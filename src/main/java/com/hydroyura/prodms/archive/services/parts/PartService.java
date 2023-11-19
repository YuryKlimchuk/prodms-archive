package com.hydroyura.prodms.archive.services.parts;


import com.hydroyura.prodms.archive.data.entities.DBPart;
import com.hydroyura.prodms.archive.data.entities.dto.DTOPart;
import com.hydroyura.prodms.archive.data.entities.enums.DBPartStatus;
import com.hydroyura.prodms.archive.data.repositories.BaseRepository;
import com.hydroyura.prodms.archive.services.predicates.IPredicateGenerator;
import com.hydroyura.prodms.archive.services.publisher.Publisher;
import com.hydroyura.prodms.archive.services.publisher.partchange.PartChangeEvent;
import com.hydroyura.prodms.archive.services.publisher.partchange.PartChangeEventType;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service(value = "PartService")
public class PartService implements IPartService<DTOPart, String> {

    private Logger logger = LoggerFactory.getLogger(PartService.class);
    private Class<DTOPart> dtoType = DTOPart.class;
    private Class<DBPart> entityType = DBPart.class;
    private Executor executor = Executors.newSingleThreadExecutor();

    @Autowired
    private ModelMapper modelMapper;

    @Autowired @Qualifier(value = "PartRepository")
    private BaseRepository<DBPart, String> repository;

    @Autowired @Qualifier(value = "PartPredicateGenerator")
    private IPredicateGenerator predicateGenerator;

    @Autowired @Qualifier(value = "partChangePublisher")
    private Publisher publisher;


    @Override
    public Optional<DTOPart> getItemById(String id) {
        Optional<DBPart> entity = repository.findById(id);
        if (entity.isEmpty()) {
            logger.info("DBPart with ID = [{}] not found", id);
            return Optional.empty();
        }
        DTOPart dto = modelMapper.map(entity.get(), dtoType);
        return Optional.of(dto);
    }

    @Override
    public Collection<DTOPart> getAllByFilter(Map<String, String> filter) {
        return StreamSupport.stream(repository.findAll(predicateGenerator.generate(filter)).spliterator(), false)
                        .map(entity -> modelMapper.map(entity, dtoType))
                        .collect(Collectors.toList());
    }

    @Override
    public Optional<DTOPart> delete(String id) {
        Optional<DBPart> dbPart = repository.findById(id);
        if (dbPart.isEmpty()) {
            logger.info("DBPart with ID = [{}] not found", id);
            return Optional.empty();
        }
        repository.save(dbPart.get().setStatus(DBPartStatus.DELETED).setVersion(dbPart.get().getVersion() + 1));
        DTOPart dtoPart = modelMapper.map(dbPart.get(), DTOPart.class);
        publisher.sendEvent(new PartChangeEvent(PartChangeEventType.DELETED, dbPart.get(), DBPart.class));
        return Optional.of(dtoPart);
    }

    @Override
    public Optional<DTOPart> create(DTOPart dto) {
        dto.setVersion(1L);
        dto.setCreated(LocalDate.now());
        dto.setUpdated(LocalDate.now());
        DBPart entity = modelMapper.map(dto, entityType);

        DTOPart savedDTO = null;
        if (repository.existsById(dto.getNumber())) {
            logger.info("Cannot create DBPart with ID = [{}] already exists", dto.getNumber());
        } else {
            try {
                DBPart savedEntity = repository.saveAndFlush(entity);
                savedDTO = modelMapper.map(savedEntity, dtoType);
                publisher.sendEvent(new PartChangeEvent(PartChangeEventType.CREATED, savedEntity, DBPart.class));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.ofNullable(savedDTO);
    }


    @Override
    public Optional<DTOPart> update(DTOPart updated) {
        Optional<DTOPart> current = getItemById(updated.getNumber());
        if(current.isEmpty()) {
            logger.info("Item with ID = {} => Not found", updated.getNumber());
            return Optional.empty();
        }

        /*
        Map<String, String> difference = objectComparator.getDifference(DTOPart.class, current.get(), updated);
        if(difference.isEmpty()) {
            logger.info("Nothing to update, updatedObject the same with current version", updated.getNumber());
            return Optional.empty();
        }
         */

        DTOPart updatedResult;
        try {
            updated.setVersion(current.get().getVersion() + 1L);
            updated.setUpdated(LocalDate.now());
            updatedResult = modelMapper.map(repository.save(modelMapper.map(updated, entityType)), dtoType);
        } catch (Exception e) {
            logger.info("DB error while updating item with ID = {} => e = {}", updated.getNumber(), e);
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(updatedResult);
    }


}
