package com.hydroyura.prodms.archive.server.services.mappers;

import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnit;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitUpdate;
import com.hydroyura.prodms.archive.client.dtos.unit.request.UnitCreateReq;
import com.hydroyura.prodms.archive.client.dtos.unit.response.UnitCreateRes;
import com.hydroyura.prodms.archive.server.entities.Unit;
import com.hydroyura.prodms.archive.server.services.mappers.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersConfig {


    @Bean
    BaseMapper<Unit, UnitCreateReq> unitCreateReqMapper() {
        return new UnitCreateReqMapperImpl();
    }

    @Bean
    BaseMapper<Unit, UnitCreateRes> unitCreateResMapper() {
        return new UnitCreateResMapperImpl();
    }



}
