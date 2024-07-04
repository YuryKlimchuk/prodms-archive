package com.hydroyura.prodms.archive.server.repositories;


import com.hydroyura.prodms.archive.client.dtos.unit.filter.FilterUnit;
import com.hydroyura.prodms.archive.server.entities.Unit;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface UnitRepository extends BaseRepository<Unit, String, FilterUnit> {
    Optional<String> create(Unit unit);
    Optional<Unit> findOne(String number);
    Boolean delete(String number);
    Boolean update(Unit unit);
    Collection<Unit> findMany(FilterUnit filterUnit);
}
