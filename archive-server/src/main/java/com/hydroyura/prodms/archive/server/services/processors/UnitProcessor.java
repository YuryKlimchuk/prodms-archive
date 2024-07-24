package com.hydroyura.prodms.archive.server.services.processors;

import com.hydroyura.prodms.archive.client.unit.request.UnitCreateReq;
import com.hydroyura.prodms.archive.client.unit.response.UnitCreateRes;

public interface UnitProcessor {

    UnitCreateRes create(UnitCreateReq dto);
    /*
    Optional<DTOUnit> findOne(String number);
    Boolean delete(String number);
    Collection<DTOUnit> findMany(FilterUnit filter);
    Boolean update(DTOUnitUpdate dto);

     */
}