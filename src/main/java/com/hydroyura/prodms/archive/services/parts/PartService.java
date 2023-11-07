package com.hydroyura.prodms.archive.services.parts;


import com.hydroyura.prodms.archive.data.entities.DBPart;
import com.hydroyura.prodms.archive.data.repositories.BaseRepository;
import com.hydroyura.prodms.archive.dto.DTOPart;
import com.hydroyura.prodms.archive.dto.DTOPartChange;
import com.hydroyura.prodms.archive.services.listeners.EntityChangeMessage;
import com.hydroyura.prodms.archive.services.listeners.IEntityChangeListener;
import com.hydroyura.prodms.archive.services.objectcomparator.IObjectComparator;
import com.hydroyura.prodms.archive.services.objectcomparator.ObjectComparator;
import com.hydroyura.prodms.archive.services.predicates.IPredicateGenerator;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service(value = "PartService")
public class PartService implements IPartService<DTOPart, String> {

    private Logger logger = LoggerFactory.getLogger(PartService.class);
    private Class<DTOPart> dtoType = DTOPart.class;
    private Class<DBPart> entityType = DBPart.class;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired @Qualifier(value = "PartRepository")
    private BaseRepository<DBPart, String> repository;

    @Autowired @Qualifier(value = "PartPredicateGenerator")
    private IPredicateGenerator predicateGenerator;

    @Autowired @Qualifier(value = "PartListener")
    private IEntityChangeListener<DTOPart> listener;

    @Autowired @Qualifier(value = "ObjectComparator")
    private IObjectComparator objectComparator;

    private Executor executor = Executors.newSingleThreadExecutor();


    @Override
    public Optional<DTOPart> getItemById(String id) {
        logger.info("Attempt to receive item with ID = {}", id);

        Optional<DBPart> entity = repository.findById(id);
        if(entity.isEmpty()) {
            logger.info("Item with ID = {} => not found", id);
            return Optional.empty();
        }

        DTOPart dto = modelMapper.map(entity.get(), dtoType);
        logger.info("Item with ID = {} => received from DB successfully", id);
        return Optional.of(dto);
    }

    @Override
    public Collection<DTOPart> getAllByFilter(Map<String, String> filter) {
        logger.info("Attempt to receiving items by filter = {}", filter);
        return
                StreamSupport.stream(repository.findAll(predicateGenerator.generate(filter)).spliterator(), false)
                        .map(entity -> modelMapper.map(entity, dtoType))
                        .collect(Collectors.toList());
    }

    @Override
    public Optional<DTOPart> delete(String id) {
        logger.info("Attempt to delete item with ID = {}", id);
        Optional<DBPart> dbPart = repository.findById(id);

        if(dbPart.isEmpty()) {
            logger.info("Item with ID = {} => not found", id);
            return Optional.empty();
        }

        repository.deleteById(id);
        DTOPart dtoPart = modelMapper.map(dbPart.get(), DTOPart.class);
        logger.info("Item with ID = {} => deleted from DB successfully", id);
        return Optional.of(dtoPart);
    }

    @Override
    public Optional<DTOPart> create(DTOPart dto) {
        logger.info("Attempt to create item with ID = {}", dto.getNumber());

        dto.setVersion(1L);
        dto.setCreated(LocalDate.now());
        dto.setUpdated(LocalDate.now());
        DBPart entity = modelMapper.map(dto, entityType);

        DTOPart savedDTO = null;
        if(repository.existsById(dto.getNumber())) {
            logger.info("ID = {} => Already exists", dto.getNumber());
        } else {
            try {
                savedDTO = modelMapper.map(repository.save(entity), dtoType);
                EntityChangeMessage msg = new EntityChangeMessage.EntityChangeMessageBuilder()
                        .setOperation("CREATE")
                        .setObject(savedDTO)
                        .setMap(Collections.EMPTY_MAP)
                        .build();
                executor.execute(() -> listener.receiveMessage(msg));
            } catch (Exception e) {
                logger.info("DB error while creating item with ID = {} => e = {}", dto.getNumber(), e);
                throw new RuntimeException(e);
            }
        }
        return Optional.ofNullable(savedDTO);
    }


    @Override
    public Optional<DTOPart> update(DTOPart updated) {
        logger.info("Attempt to update item with ID = {}", updated.getNumber());

        Optional<DTOPart> current = getItemById(updated.getNumber());
        if(current.isEmpty()) {
            logger.info("Item with ID = {} => Not found", updated.getNumber());
            return Optional.empty();
        }

        Map<String, String> difference = objectComparator.getDifference(DTOPart.class, current.get(), updated);
        if(difference.isEmpty()) {
            logger.info("Nothing to update, updatedObject the same with current version", updated.getNumber());
            return Optional.empty();
        }

        DTOPart updatedResult = null;
        try {
            updated.setVersion(current.get().getVersion() + 1L);
            updated.setUpdated(LocalDate.now());
            updatedResult = modelMapper.map(repository.save(modelMapper.map(updated, entityType)), dtoType);
            EntityChangeMessage msg = new EntityChangeMessage.EntityChangeMessageBuilder()
                    .setOperation("UPDATE")
                    .setObject(updatedResult)
                    .setMap(difference)
                    .build();
            executor.execute(() -> listener.receiveMessage(msg));
        } catch (Exception e) {
            logger.info("DB error while updating item with ID = {} => e = {}", updated.getNumber(), e);
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(updatedResult);
    }


}
