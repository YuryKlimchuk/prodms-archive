package com.hydroyura.prodms.archive.data.repositories;

import com.hydroyura.prodms.archive.data.entities.DBPart;
import com.hydroyura.prodms.archive.data.entities.DBRate;
import com.hydroyura.prodms.archive.data.entities.enums.DBPartStatus;
import com.hydroyura.prodms.archive.data.entities.enums.DBPartType;
import com.hydroyura.prodms.archive.data.entities.keys.DBRateKey;
import com.hydroyura.prodms.archive.services.rates.predicates.IPredicateGenerator;
import com.hydroyura.prodms.archive.services.rates.predicates.PartRatePredicateGenerator;
import com.querydsl.core.types.Predicate;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RateRepositoryTest {

    @Autowired
    private PartRepository partRepository;
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private EntityManager em;


    @Test
    void injections() {
        assertNotNull(partRepository);
        assertNotNull(rateRepository);
        assertNotNull(em);
    }

    @Test
    void create() {
        String ID1 = "ID1";
        partRepository.save(createPart(ID1));
        String ID2 = "ID2";
        partRepository.save(createAssembly(ID2));
        em.flush();
        assertEquals(2, partRepository.count());

        DBRate rate1 = createRate(partRepository.getReferenceById(ID2), partRepository.getReferenceById(ID1), 2);
        rateRepository.save(rate1);
        em.flush();
        assertEquals(1, rateRepository.count());
    }

    @Test
    void getRatesBYAssemblyId() {
        IPredicateGenerator predicateGenerator = new PartRatePredicateGenerator();
        String ID1 = "ID1";
        partRepository.save(createPart(ID1));
        String ID2 = "ID2";
        partRepository.save(createAssembly(ID2));
        em.flush();
        assertEquals(2, partRepository.count());
        long count = 2;
        DBRate rate1 = createRate(partRepository.getReferenceById(ID2), partRepository.getReferenceById(ID1), count);
        rateRepository.save(rate1);

        Map<String, String> params = new HashMap<>();
        params.put("assembly-number", "ID2");
        Predicate predicate = predicateGenerator.generate(params);
        List<DBRate> rates = StreamSupport.stream(rateRepository.findAll(predicate).spliterator(), false).collect(Collectors.toCollection(ArrayList::new));
        assertEquals(1, rates.size());
        assertEquals(count, rates.get(0).getCount());
    }

    DBPart createPart(String id) {
        DBPart part = new DBPart()
                .setNumber(id)
                .setName("TEST_NAME_ASM")
                .setType(DBPartType.PART)
                .setStatus(DBPartStatus.TEST);
        return part;
    }

    DBPart createAssembly(String id) {
        DBPart part = new DBPart()
                .setNumber(id)
                .setName("TEST_NAME_ASM")
                .setType(DBPartType.ASSEMBLY)
                .setStatus(DBPartStatus.TEST);
        return part;
    }

    DBRate createRate(DBPart assembly, DBPart part, long count) {
        DBRate rate = new DBRate();
        rate.setCount(count);
        rate.setElement(part);
        rate.setAssembly(assembly);
        DBRateKey key = new DBRateKey()
                .setElementId(part.getNumber())
                .setAssemblyId(assembly.getNumber());
        rate.setKey(key);
        return rate;
    }

}