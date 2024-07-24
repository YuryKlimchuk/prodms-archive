package com.hydroyura.prodms.archive.server.services.mappers;

import com.hydroyura.prodms.archive.client.unit.request.UnitCreateReq;
import com.hydroyura.prodms.archive.client.unit.response.UnitCreateRes;
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
