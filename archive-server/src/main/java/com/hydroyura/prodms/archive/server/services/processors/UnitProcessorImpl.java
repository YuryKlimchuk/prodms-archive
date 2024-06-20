package com.hydroyura.prodms.archive.server.services.processors;

import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnit;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitUpdate;
import com.hydroyura.prodms.archive.client.dtos.unit.filter.FilterUnit;
import com.hydroyura.prodms.archive.server.repositories.UnitRepository;
import com.hydroyura.prodms.archive.server.services.mappers.MappersMngr;
import com.hydroyura.prodms.archive.server.services.predicates.UnitPredicateGenerator;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UnitProcessorImpl implements UnitProcessor {

    private final UnitRepository repository;
    private final UnitPredicateGenerator predicateGenerator;
    private final MappersMngr mappersMngr;

    public UnitProcessorImpl(UnitRepository repository, UnitPredicateGenerator predicateGenerator, MappersMngr mappersMngr) {
        this.repository = repository;
        this.predicateGenerator = predicateGenerator;
        this.mappersMngr = mappersMngr;
    }

    @Override
    public Optional<String> create(DTOUnitCreate dto) {
        return Optional.empty();
    }

    @Override
    public Optional<DTOUnit> findOne(String number) {
        return Optional.empty();
    }

    @Override
    public Collection<DTOUnit> findMany(FilterUnit filter) {
        return null;
    }

    @Override
    public Boolean update(DTOUnitUpdate dto) {
        return null;
    }

    @Override
    public Boolean delete(String number) {
        return null;
    }
}
