package com.hydroyura.prodms.archive.server.services.mappers.impl;

import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import com.hydroyura.prodms.archive.client.dtos.unit.request.UnitCreateReq;
import com.hydroyura.prodms.archive.server.entities.Unit;
import com.hydroyura.prodms.archive.server.services.mappers.BaseMapper;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface UnitCreateReqMapper extends BaseMapper<Unit, UnitCreateReq> {

    Map.Entry<Class<Unit>, Class<UnitCreateReq>> TYPE = Map.entry(Unit.class, UnitCreateReq.class);

    @Override
    default Map.Entry<Class<Unit>, Class<UnitCreateReq>> getType() {
        return TYPE;
    }

}
