package com.hydroyura.prodms.archive.services.changes;

import com.hydroyura.prodms.archive.data.entities.DBPart;
import com.hydroyura.prodms.archive.data.entities.DBPartChange;
import com.hydroyura.prodms.archive.data.entities.keys.DBPartChangeKey;
import com.hydroyura.prodms.archive.data.repositories.BaseRepository;
import com.hydroyura.prodms.archive.data.entities.dto.DTOPartChange;
import com.hydroyura.prodms.archive.services.parts.PartService;
import com.hydroyura.prodms.archive.services.predicates.IPredicateGenerator;
import com.querydsl.core.types.Predicate;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component(value = "PartChangeService")
public class PartChangeService implements IPartChangeService {

    private Logger logger = LoggerFactory.getLogger(PartService.class);
    private Class<DTOPartChange> dtoType = DTOPartChange.class;
    private Class<DBPartChange> entityType = DBPartChange.class;

    @Autowired @Qualifier(value = "PartChangePredicateGenerator")
    private IPredicateGenerator predicateGenerator;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired @Qualifier(value = "PartChangeRepository")
    private BaseRepository<DBPartChange, DBPartChangeKey> repository;


    @Override
    public Collection<DTOPartChange> getChanges(String number) {
        Predicate predicate = predicateGenerator.generate(Map.of("NUMBER", number));
        Sort sort = Sort.by(Sort.Direction.ASC, "key.version");

        return StreamSupport
                    .stream(repository.findAll(predicate, sort).spliterator(), false)
                    .map(entity -> modelMapper.map(entity, dtoType))
                    .collect(Collectors.toList());
    }

    @Override
    public void create(DBPartChange partChange) {
        repository.save(partChange);
    }

}
