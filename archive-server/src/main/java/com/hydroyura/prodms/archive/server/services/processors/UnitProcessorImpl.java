package com.hydroyura.prodms.archive.server.services.processors;

import com.hydroyura.prodms.archive.client.dtos.api.SearchOptions;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnit;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import com.hydroyura.prodms.archive.client.dtos.unit.filter.FilterUnit;
import com.hydroyura.prodms.archive.server.entities.Unit;
import com.hydroyura.prodms.archive.server.repositories.UnitRepository;
import com.hydroyura.prodms.archive.server.services.mappers.BaseMapper;
import com.hydroyura.prodms.archive.server.services.mappers.MappersMngr;
import com.hydroyura.prodms.archive.server.services.predicates.PredicateGenerator;
import com.hydroyura.prodms.archive.server.services.validators.DTOValidatorMngr;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class UnitProcessorImpl implements UnitProcessor {

    @Autowired
    private MappersMngr mappersMngr;

    @Autowired
    private DTOValidatorMngr validatorMngr;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private PredicateGenerator<FilterUnit> predicateGenerator;

    @Override
    public Optional<String> create(DTOUnitCreate dto) {
        validatorMngr.validate(dto);
        Unit unit = mappersMngr
                .getMapper(Map.entry(Unit.class, DTOUnitCreate.class))
                .destinationToSource(dto);
        return unitRepository.create(unit);
    }

    @Override
    public Optional<DTOUnit> findOne(String number) {
        validatorMngr.validate(number);
        return unitRepository.findOne(number)
                            .map(unit -> mappersMngr
                                    .getMapper(Map.entry(Unit.class, DTOUnit.class))
                                    .sourceToDestination(unit));
    }

    @Override
    public Boolean delete(String number) {
        validatorMngr.validate(number);
        return unitRepository.delete(number);
    }

    @Override
    public Collection<DTOUnit> findMany(SearchOptions searchOptions) {
        validatorMngr.validate(searchOptions);
        FilterUnit filterUnit = new FilterUnit();
        Optional<Predicate> predicate = predicateGenerator.generate(filterUnit);
        BaseMapper<Unit, DTOUnit> mapper =  mappersMngr.getMapper(Map.entry(Unit.class, DTOUnit.class));
        return unitRepository.findMany(predicate.get())
                .stream()
                .map(mapper::sourceToDestination)
                .toList();
    }
}
