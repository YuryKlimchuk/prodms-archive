package com.hydroyura.prodms.archive.services.parts;

import com.hydroyura.prodms.archive.data.entities.*;
import com.hydroyura.prodms.archive.data.repositories.BaseRepository;
import com.hydroyura.prodms.archive.dto.DTOPart;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PartServiceTest {

    @Autowired @Qualifier(value = "PartService")
    IPartService<DTOPart, String> service;

    @Autowired @Qualifier(value = "PartRepository")
    BaseRepository<DBPart, String> repository;

    @Autowired @Qualifier(value = "PartChangeRepository")
    BaseRepository<DBPartChange, DBPartChangeKey> changeRepository;

    @Test
    void getItemById() {
        DBPart part = createPart();
        String id = "BPG100-00001";
        part.setNumber(id);
        repository.save(part);

        assertEquals(id, service.getItemById(id).get().getNumber());
        assertTrue(service.getItemById(id + "12").isEmpty());
    }

    @Test
    void getAllByFilter() {

        repository.save(createPart("RGR100-11001", DBPartStatus.DESIGN, DBPartType.PART));
        repository.save(createPart("RGR100-11002", DBPartStatus.DESIGN, DBPartType.PART));
        repository.save(createPart("RGR100-11003", DBPartStatus.DESIGN, DBPartType.ASSEMBLY));
        repository.save(createPart("RGR250-11004", DBPartStatus.PRODUCTION, DBPartType.ASSEMBLY));
        repository.save(createPart("RGR250-11005", DBPartStatus.TEST, DBPartType.BUY_PART));
        repository.save(createPart("RGR100-11006", DBPartStatus.TEST, DBPartType.BUY_PART));
        assertEquals(6, repository.findAll().size());

        Map<String, String> filter = new HashMap<>();
        filter.put("STATUS", "DESIGN");
        assertEquals(3, service.getAllByFilter(filter).size());

        filter.put("TYPE", "ASSEMBLY");
        assertEquals(1, service.getAllByFilter(filter).size());

        filter.clear();
        filter.put("NUMBER", "RGR250");
        assertEquals(2, service.getAllByFilter(filter).size());

        filter.clear();
        filter.put("NUMBER", "RGR100");
        filter.put("TYPE", "PART");
        filter.put("STATUS", "DESIGN");
        assertEquals(2, service.getAllByFilter(filter).size());

    }

    @Test
    void delete() {
        DBPart part = createPart();
        String id = "BPG100-00001";
        part.setNumber(id);
        repository.save(part);

        DBPart part1 = createPart();
        String id1= "BPG100-00002";
        part1.setNumber(id1);
        repository.save(part1);

        assertEquals(2, repository.findAll().size());

        service.delete(id);
        assertEquals(1, repository.findAll().size());
        service.delete(id1);
        assertEquals(0, repository.findAll().size());
    }

    @Test
    void create() {
        DTOPart part1 = createPartDTO();
        service.create(part1);
        assertEquals(1, service.getAllByFilter(null).size());

        service.create(part1);
        assertTrue(service.create(part1).isEmpty());

        Collection<DBPartChange> changes = changeRepository.findAll();
        assertEquals(1, changes.size());
        assertEquals("CREATE", changes.stream().findAny().get().getOperation());
    }

    @Test
    void update() {
        DTOPart part1 = createPartDTO();
        String part1Number = "RGR100-82001";
        part1.setNumber(part1Number);
        String part1Name = "Shaft";
        part1.setName(part1Name);
        service.create(part1);
        part1.setName("Mini-shaft");
        service.update(part1);
        assertEquals(2, changeRepository.findAll().size());

        DTOPart updatedPart = service.getItemById(part1Number).get();
        assertEquals(2, updatedPart.getVersion());
        assertEquals("Mini-shaft", updatedPart.getName());
    }


    DBPart createPart() {
        DBPart part = new DBPart();
        part.setVersion(1L);
        part.setCreated(LocalDate.now());
        part.setUpdated(LocalDate.now());
        part.setPdf(null);
        part.setOtherFile(null);
        part.setType(DBPartType.PART);
        part.setStatus(DBPartStatus.PRODUCTION);
        part.setInfo(null);
        part.setName("Ось");
        part.setNumber("RGR100-15006");
        return part;
    }

    DBPart createPart(String number, DBPartStatus status, DBPartType type) {
        DBPart part = new DBPart();
        part.setVersion(1L);
        part.setCreated(LocalDate.now());
        part.setUpdated(LocalDate.now());
        part.setPdf(null);
        part.setOtherFile(null);
        part.setType(type);
        part.setStatus(status);
        part.setInfo(null);
        part.setName("Ось");
        part.setNumber(number);
        return part;
    }

    DTOPart createPartDTO() {
        DTOPart part = new DTOPart();
        part.setVersion(1L);
        part.setCreated(LocalDate.now());
        part.setUpdated(LocalDate.now());
        part.setPdf(null);
        part.setOtherFile(null);
        part.setType(DBPartType.PART);
        part.setStatus(DBPartStatus.PRODUCTION);
        part.setInfo(null);
        part.setName("Ось");
        part.setNumber("RGR100-15006");
        return part;
    }


    @BeforeEach @AfterEach
    void clearDB() {
        repository.deleteAll();
        changeRepository.deleteAll();
    }

}