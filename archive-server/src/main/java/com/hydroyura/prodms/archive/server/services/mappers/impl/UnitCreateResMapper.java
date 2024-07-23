package com.hydroyura.prodms.archive.server.services.mappers.impl;

import com.hydroyura.prodms.archive.client.dtos.unit.response.UnitCreateRes;
import com.hydroyura.prodms.archive.server.entities.Unit;
import com.hydroyura.prodms.archive.server.services.mappers.BaseMapper;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface UnitCreateResMapper extends BaseMapper<Unit, UnitCreateRes> {

    Map.Entry<Class<Unit>, Class<UnitCreateRes>> TYPE = Map.entry(Unit.class, UnitCreateRes.class);

    @Override
    default Map.Entry<Class<Unit>, Class<UnitCreateRes>> getType() {
        return TYPE;
    }

}
