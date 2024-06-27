package com.hydroyura.prodms.archive.server.services.mappers;

import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnit;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitCreate;
import com.hydroyura.prodms.archive.client.dtos.unit.dto.DTOUnitUpdate;
import com.hydroyura.prodms.archive.server.entities.Unit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MappersMngrTest {

    @Autowired
    private MappersMngr mappersMngr;


    @Test
    void injection() {
        assertNotNull(mappersMngr);
    }

    @Test
    void mapperList() {
        assertNotNull(mappersMngr.getMapper(Map.entry(Unit.class, DTOUnitCreate.class)));
        assertNotNull(mappersMngr.getMapper(Map.entry(Unit.class, DTOUnit.class)));
        assertNotNull(mappersMngr.getMapper(Map.entry(Unit.class, DTOUnitUpdate.class)));
    }

}