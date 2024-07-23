package com.hydroyura.prodms.archive.server.services.processors;

import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnit;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitUpdate;
import com.hydroyura.prodms.archive.client.dtos.unit.filter.FilterUnit;
import com.hydroyura.prodms.archive.client.dtos.unit.request.UnitCreateReq;
import com.hydroyura.prodms.archive.client.dtos.unit.response.UnitCreateRes;

import java.util.Collection;
import java.util.Optional;

public interface UnitProcessor {

    UnitCreateRes create(UnitCreateReq dto);
    /*
    Optional<DTOUnit> findOne(String number);
    Boolean delete(String number);
    Collection<DTOUnit> findMany(FilterUnit filter);
    Boolean update(DTOUnitUpdate dto);

     */
}