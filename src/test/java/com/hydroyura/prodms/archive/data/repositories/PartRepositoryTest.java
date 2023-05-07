package com.hydroyura.prodms.archive.data.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.prodms.archive.data.entities.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PartRepositoryTest {

    @Autowired @Qualifier(value = "PartRepository")
    BaseRepository<DBPart, String> repository;

    @Autowired @Qualifier(value = "PartChangeRepository")
    BaseRepository<DBPartChange, DBPartChangeKey> changeRepository;

    @Autowired @Qualifier(value = "RateRepository")
    BaseRepository<DBRate, DBRateKey> rateRepository;

    @Autowired
    EntityManager entityManager;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void initTest() {
        assertNotNull(changeRepository);
        assertNotNull(repository);
        assertNotNull(entityManager);
    }

    @Test
    void createTest() {
        DBPart part1 = createPart();
        DBPart part2 = createPart();
        part2.setNumber("RGR100-15009");

        repository.save(part1);
        repository.save(part2);

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void deleteTest() {
        DBPart part = createPart();
        String key = "RGR100-500";
        part.setNumber(key);

        repository.saveAndFlush(part);
        assertEquals(1, repository.findAll().size());

        repository.deleteById(key);
        assertEquals(0, repository.findAll().size());
    }


    @Test
    void simpleRateTest() {
        DBPart asm1 = createAssembly();
        String asm1Num = "RGR100-00000 СБ";
        asm1.setNumber(asm1Num);

        DBPart part1 = createPart();
        String part11Num = "RGR100-00001";
        part1.setNumber(part11Num);


        DBPart part2 = createPart();
        String part21Num = "RGR100-00002";
        part2.setNumber(part21Num);


        repository.save(asm1);
        repository.save(part1);
        repository.save(part2);
        assertEquals(3, repository.findAll().size());

        DBRate rate1 = new DBRate();
        rate1.setCount(3L);
        DBRateKey rate1Key = new DBRateKey();
        rate1.setKey(rate1Key);
        rate1.setAssembly(repository.getReferenceById(asm1Num));
        rate1.setElement(repository.getReferenceById(part11Num));
        rateRepository.save(rate1);

        DBRate rate2 = new DBRate();
        rate2.setCount(1L);
        DBRateKey rate2Key = new DBRateKey();
        rate2.setKey(rate2Key);
        rate2.setAssembly(repository.getReferenceById(asm1Num));
        rate2.setElement(repository.getReferenceById(part21Num));
        rateRepository.save(rate2);

        assertEquals(2, rateRepository.findAll().size());

        Collection<DBRate> rates = StreamSupport.stream(rateRepository.findAll(QDBRate.dBRate.assembly.number.eq(asm1Num)).spliterator(), false).collect(Collectors.toList());
        assertEquals(2, rates.size());
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


    DBPart createAssembly() {
        DBPart part = new DBPart();
        part.setVersion(1L);
        part.setCreated(LocalDate.now());
        part.setUpdated(LocalDate.now());
        part.setPdf(null);
        part.setOtherFile(null);
        part.setType(DBPartType.ASSEMBLY);
        part.setStatus(DBPartStatus.PRODUCTION);
        part.setInfo(null);
        part.setName("Ось");
        part.setNumber("RGR100-15006");
        return part;
    }

    DBPartChange createChange(DBPart part) {
        DBPartChangeKey key = new DBPartChangeKey();
        key.setVersion(part.getVersion());
        key.setPartNumber(part.getNumber());

        DBPartChange change = new DBPartChange();
        change.setKey(key);

        change.setUpdate(part.getUpdated());
        change.setFieldName("");
        change.setFieldValue("");
        try {
            change.setObject(mapper.writeValueAsString(part));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        change.setOperation("CREATE");
        change.setUser("YURY_KLIMCHUK");
        change.setPart(part);

        return change;
    }

}