package com.hydroyura.prodms.archive.server.services.mappers.impl;

import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitUpdate;
import com.hydroyura.prodms.archive.server.entities.Unit;
import com.hydroyura.prodms.archive.server.services.mappers.BaseMapper;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface UnitUpdateMapper extends BaseMapper<Unit, DTOUnitUpdate> {

    Map.Entry<Class<Unit>, Class<DTOUnitUpdate>> TYPE = Map.entry(Unit.class, DTOUnitUpdate.class);

    @Override
    default Map.Entry<Class<Unit>, Class<DTOUnitUpdate>> getType() {
        return TYPE;
    }

}
