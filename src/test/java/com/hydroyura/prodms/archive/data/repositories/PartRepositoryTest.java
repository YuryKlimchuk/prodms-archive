package com.hydroyura.prodms.archive.data.repositories;

import com.hydroyura.prodms.archive.data.entities.DBPart;
import com.hydroyura.prodms.archive.data.entities.enums.DBPartStatus;
import com.hydroyura.prodms.archive.data.entities.enums.DBPartType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PartRepositoryTest {

    @Autowired
    private PartRepository partRepository;
    @Autowired
    private EntityManager em;


    @Test
    void injections() {
        assertNotNull(partRepository);
        assertNotNull(em);
    }


    @Test
    void create() {
        String id = "TEST_NUMBER";
        DBPart part = new DBPart()
                .setName("NAME")
                .setNumber(id)
                .setStatus(DBPartStatus.TEST)
                .setType(DBPartType.PART);
        partRepository.save(part);
        em.flush();

        Optional<DBPart> result = partRepository.findById(id);
        assertEquals(id, result.get().getNumber());
    }
}