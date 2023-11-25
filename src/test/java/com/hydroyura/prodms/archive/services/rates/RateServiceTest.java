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
        partService.create(createPart("PART_ID"));
        String ASSEMBLY_ID = "ASSEMBLY_ID";
        partService.create(createAssembly("ASSEMBLY_ID"));
        em.flush();

        DTORate rate = rateService.create(ASSEMBLY_ID, PART_ID, 10).get();
        em.flush();

        assertEquals(1, rateRepository.count());
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