package com.hydroyura.prodms.archive.server.repositories;


import com.hydroyura.prodms.archive.client.unit.FilterUnit;
import com.hydroyura.prodms.archive.server.entities.Unit;

public interface UnitRepository extends BaseRepository<Unit, String, FilterUnit> {
    String create(Unit unit);
    /*
    Optional<Unit> findOne(String number);
    Boolean delete(String number);
    Boolean update(Unit unit);
    Collection<Unit> findMany(FilterUnit filterUnit);
     */
}
