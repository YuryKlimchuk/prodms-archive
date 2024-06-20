package com.hydroyura.prodms.archive.server.services.mappers.impl;

import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnit;
import com.hydroyura.prodms.archive.server.entities.Unit;
import com.hydroyura.prodms.archive.server.services.mappers.BaseMapper;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface UnitMapper extends BaseMapper<Unit, DTOUnit> {

    Map.Entry<Class<Unit>, Class<DTOUnit>> TYPE = Map.entry(Unit.class, DTOUnit.class);

    @Override
    default Map.Entry<Class<Unit>, Class<DTOUnit>> getType() {
        return TYPE;
    }

}
