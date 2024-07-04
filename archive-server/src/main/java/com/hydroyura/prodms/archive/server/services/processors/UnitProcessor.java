package com.hydroyura.prodms.archive.server.services.processors;

import com.hydroyura.prodms.archive.client.dtos.api.SearchOptions;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnit;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import com.hydroyura.prodms.archive.client.dtos.unit.filter.FilterUnit;

import java.util.Collection;
import java.util.Optional;

public interface UnitProcessor {

    Optional<String> create(DTOUnitCreate dto);
    Optional<DTOUnit> findOne(String number);
    Boolean delete(String number);
    Collection<DTOUnit> findMany(FilterUnit filter);
}


/*
    Boolean update(Unit unit);
    Collection<Unit> findMany(Predicate predicate);
 */