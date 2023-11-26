package com.hydroyura.prodms.archive.services.rates;

import com.hydroyura.prodms.archive.data.entities.dto.DTOPart;
import com.hydroyura.prodms.archive.data.entities.dto.DTORate;
import com.hydroyura.prodms.archive.data.entities.enums.DBPartStatus;
import com.hydroyura.prodms.archive.data.entities.enums.DBPartType;
import com.hydroyura.prodms.archive.data.repositories.RateRepository;
import com.hydroyura.prodms.archive.services.parts.PartService;
import com.hydroyura.prodms.archive.services.parts.PartServiceTestConfiguration;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Import({RateServiceTestConfiguration.class, PartServiceTestConfiguration.class})
@DataJpaTest
class RateServiceTest {

    @Autowired
    private RateService rateService;

    @Autowired
    private PartService partService;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private EntityManager em;

    @Test
    void injection() {
        assertNotNull(rateService);
    }

    @Test
    void create() {
        String PART_ID = "PART_ID";
        partService.create(createPart(PART_ID));
        String ASSEMBLY_ID = "ASSEMBLY_ID";
        partService.create(createAssembly(ASSEMBLY_ID));
        em.flush();

        DTORate rate = rateService.create(ASSEMBLY_ID, PART_ID, 10).get();
        em.flush();

        assertEquals(1, rateRepository.count());
    }

    @Test
    void getRates() {
        String PART_ID0 = "PART_ID0";
        partService.create(createPart(PART_ID0));
        String PART_ID1 = "PART_ID1";
        partService.create(createPart(PART_ID1));
        String PART_ID2 = "PART_ID2";
        partService.create(createPart(PART_ID2));
        String ASSEMBLY_ID = "ASSEMBLY_ID";
        partService.create(createAssembly(ASSEMBLY_ID));
        em.flush();
        DTORate rate0 = rateService.create(ASSEMBLY_ID, PART_ID0, 2).get();
        DTORate rate1 = rateService.create(ASSEMBLY_ID, PART_ID1, 3).get();
        DTORate rate2 = rateService.create(ASSEMBLY_ID, PART_ID2, 4).get();
        em.flush();
        Collection<DTORate> rates = rateService.getRates(ASSEMBLY_ID);
        assertEquals(3, rates.size());
        assertEquals(9, rates.stream().mapToLong(DTORate::getCount).sum());
    }

    @Test
    void getAssemblies() {
        String PART_ID0 = "PART_ID";
        partService.create(createPart(PART_ID0));

        String ASSEMBLY_ID0 = "ASSEMBLY_ID0";
        partService.create(createAssembly(ASSEMBLY_ID0));
        String ASSEMBLY_ID1 = "ASSEMBLY_ID1";
        partService.create(createAssembly(ASSEMBLY_ID1));
        String ASSEMBLY_ID2 = "ASSEMBLY_ID2";
        partService.create(createAssembly(ASSEMBLY_ID2));
        em.flush();

        rateService.create(ASSEMBLY_ID0, PART_ID0, 2).get();
        rateService.create(ASSEMBLY_ID1, PART_ID0, 3).get();
        rateService.create(ASSEMBLY_ID2, PART_ID0, 5).get();
        em.flush();
        Collection<String> assemblies = rateService.getAssemblies(PART_ID0).stream().map(DTOPart::getNumber).toList();

        assertEquals(3, assemblies.size());
        assertEquals(true, assemblies.contains(ASSEMBLY_ID0));
        assertEquals(true, assemblies.contains(ASSEMBLY_ID2));
    }

    @Test
    void changeCount() {
        String PART_ID = "PART_ID";
        partService.create(createPart(PART_ID));
        String ASSEMBLY_ID = "ASSEMBLY_ID";
        partService.create(createAssembly(ASSEMBLY_ID));
        em.flush();
        rateService.create(ASSEMBLY_ID, PART_ID, 10).get();
        em.flush();

        assertEquals(false, rateService.changeCount(ASSEMBLY_ID + 1, PART_ID + 1, 15));
        assertEquals(false, rateService.changeCount(ASSEMBLY_ID, PART_ID, 10));
        long newCount = 15;
        assertEquals(true, rateService.changeCount(ASSEMBLY_ID, PART_ID, newCount));
        em.flush();
        Optional<DTORate> updatedCountRate = rateService.getRates(ASSEMBLY_ID).stream().findFirst();
        assertEquals(newCount, updatedCountRate.get().getCount());
    }





    DTOPart createPart(String id) {
        return new DTOPart()
                .setName("TEST_PART_NAME")
                .setNumber(id)
                .setType(DBPartType.PART)
                .setStatus(DBPartStatus.TEST);
    }

    DTOPart createAssembly(String id) {
        return new DTOPart()
                .setName("TEST_ASSEMBLY_NAME")
                .setNumber(id)
                .setType(DBPartType.ASSEMBLY)
                .setStatus(DBPartStatus.TEST);
    }

}