package com.hydroyura.prodms.archive.data.repositories;

import com.hydroyura.prodms.archive.data.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class RateRepositoryTest {

    @Autowired @Qualifier(value = "RateRepository")
    private BaseRepository<DBRate, DBRateKey> rateRepository;

    @Autowired @Qualifier(value = "PartRepository")
    private BaseRepository<DBPart, String> partRepository;


    @Test
    void context() {
        assertNotNull(rateRepository);
        assertNotNull(partRepository);
    }

    @Test
    void save() {
        String asmName = "asmName";
        DBPart asm = DBEntitiesBuilders.createDBPart("asm", asmName, DBPartStatus.DESIGN, DBPartType.ASSEMBLY);
        DBPart part = DBEntitiesBuilders.createDBPart("part", "partName", DBPartStatus.DESIGN, DBPartType.PART);

        partRepository.save(asm);
        partRepository.save(part);
        assertEquals(2L, partRepository.findAll().size());

        DBRate rate = DBEntitiesBuilders.createDBRate(partRepository.getReferenceById(asm.getNumber()), partRepository.getReferenceById(part.getNumber()));
        rateRepository.save(rate);
        assertEquals(asmName, rateRepository.findAll().get(0).getAssembly().getName());
    }


    // TODO: add after create PredicateGenerator for Rates
    @Test
    void findRateByAssemblyNumber() {

    }


}
