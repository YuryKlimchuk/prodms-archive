package com.hydroyura.prodms.archive.server.services.mappers.impl;

import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import com.hydroyura.prodms.archive.server.entities.Unit;
import com.hydroyura.prodms.archive.server.services.mappers.BaseMapper;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface UnitCreateMapper extends BaseMapper<Unit, DTOUnitCreate> {

    Map.Entry<Class<Unit>, Class<DTOUnitCreate>> TYPE = Map.entry(Unit.class, DTOUnitCreate.class);

    @Override
    default Map.Entry<Class<Unit>, Class<DTOUnitCreate>> getType() {
        return TYPE;
    }

}
