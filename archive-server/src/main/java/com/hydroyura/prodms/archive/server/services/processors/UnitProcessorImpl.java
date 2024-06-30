package com.hydroyura.prodms.archive.server.services.processors;

import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import com.hydroyura.prodms.archive.server.entities.Unit;
import com.hydroyura.prodms.archive.server.repositories.UnitRepository;
import com.hydroyura.prodms.archive.server.services.mappers.MappersMngr;
import com.hydroyura.prodms.archive.server.services.validators.DTOValidatorMngr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Optional<String> create(DTOUnitCreate dto) {
        validatorMngr.validate(dto);
        Unit unit = mappersMngr.getMapper(Map.entry(Unit.class, DTOUnitCreate.class)).destinationToSource(dto);
        return unitRepository.create(unit);
    }
}
