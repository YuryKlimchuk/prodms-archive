package com.hydroyura.prodms.archive.server.services.processors;

import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnit;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitUpdate;
import com.hydroyura.prodms.archive.client.dtos.unit.filter.FilterUnit;
import com.hydroyura.prodms.archive.server.entities.Unit;
import com.hydroyura.prodms.archive.server.repositories.UnitRepository;
import com.hydroyura.prodms.archive.server.services.mappers.BaseMapper;
import com.hydroyura.prodms.archive.server.services.mappers.MappersMngr;
import com.hydroyura.prodms.archive.server.services.processors.filterchecker.FilterChecker;
import com.hydroyura.prodms.archive.server.services.validators.DTOValidatorMngr;
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
    private FilterChecker<FilterUnit> filterChecker;

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
    public Collection<DTOUnit> findMany(FilterUnit filter) {
        validatorMngr.validate(filter);
        BaseMapper<Unit, DTOUnit> mapper =  mappersMngr.getMapper(Map.entry(Unit.class, DTOUnit.class));
        FilterUnit f = filterChecker.check(filter);
        return unitRepository.findMany(f)
                .stream()
                .map(mapper::sourceToDestination)
                .toList();
    }

    @Override
    public Boolean update(DTOUnitUpdate dto) {
        validatorMngr.validate(dto);
        return unitRepository.update(
                mappersMngr
                        .getMapper(Map.entry(Unit.class, DTOUnitUpdate.class))
                        .destinationToSource(dto));
    }
}
