package com.hydroyura.prodms.archive.server.services.mappers;

import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnit;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitUpdate;
import com.hydroyura.prodms.archive.server.entities.Unit;
import com.hydroyura.prodms.archive.server.services.mappers.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersConfig {

    @Bean
    BaseMapper<Unit, DTOUnit> unitMapper() {
        return new UnitMapperImpl();
    }
    @Bean
    BaseMapper<Unit, DTOUnitCreate> unitCreateMapper() {
        return new UnitCreateMapperImpl();
    }
    @Bean
    BaseMapper<Unit, DTOUnitUpdate> unitUpdateMapper() {
        return new UnitUpdateMapperImpl();
    }


}
