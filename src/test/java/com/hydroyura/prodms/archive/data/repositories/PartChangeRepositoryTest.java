package com.hydroyura.prodms.archive.data.repositories;

import com.hydroyura.prodms.archive.data.entities.DBPart;
import com.hydroyura.prodms.archive.data.entities.DBPartChange;
import com.hydroyura.prodms.archive.data.entities.QDBPartChange;
import com.hydroyura.prodms.archive.services.publisher.partchange.PartChangeEventType;
import com.hydroyura.prodms.archive.data.entities.enums.DBPartStatus;
import com.hydroyura.prodms.archive.data.entities.enums.DBPartType;
import com.hydroyura.prodms.archive.data.entities.keys.DBPartChangeKey;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PartChangeRepositoryTest {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private PartChangeRepository partChangeRepository;

    @Autowired
    private EntityManager em;


    @Test
    void injections() {
        assertNotNull(partRepository);
        assertNotNull(partChangeRepository);
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

        DBPartChangeKey key = new DBPartChangeKey()
                .setPartNumber(partRepository.getReferenceById(id).getNumber())
                .setVersion(part.getVersion());
        DBPartChange partChange = new DBPartChange()
                .setPart(partRepository.getReferenceById(id))
                .setOperation(PartChangeEventType.CREATED)
                .setUser("YURY_KLIMCHUK")
                .setUpdate(LocalDate.now())
                .setObject(part.toString())
                .setKey(key);

        partChangeRepository.save(partChange);
        em.flush();

        assertEquals(1, partChangeRepository.findAll().size());
    }

    @Test
    void findChangesByPart() {
        String id1 = "TEST_NUMBER_1";
        DBPart part1 = new DBPart()
                .setName("NAME_1")
                .setNumber(id1)
                .setStatus(DBPartStatus.TEST)
                .setType(DBPartType.PART);
        partRepository.save(part1);
        em.flush();

        DBPartChangeKey key1 = new DBPartChangeKey()
                .setPartNumber(partRepository.getReferenceById(id1).getNumber())
                .setVersion(partRepository.getReferenceById(id1).getVersion());
        DBPartChange partChange1 = new DBPartChange()
                .setPart(partRepository.getReferenceById(id1))
                .setOperation(PartChangeEventType.CREATED)
                .setUser("YURY_KLIMCHUK")
                .setUpdate(LocalDate.now())
                .setObject(part1.toString())
                .setKey(key1);
        partChangeRepository.save(partChange1);
        em.flush();

        DBPart part1_fetched = partRepository.findById(id1).get();
        part1_fetched.setName("NAME_1_UPDATED");
        part1_fetched.setVersion(part1_fetched.getVersion() + 1);
        partRepository.save(part1_fetched);
        em.flush();

        DBPartChangeKey key11 = new DBPartChangeKey()
                .setPartNumber(partRepository.getReferenceById(id1).getNumber())
                .setVersion(partRepository.getReferenceById(id1).getVersion());
        DBPartChange partChange11 = new DBPartChange()
                .setPart(partRepository.getReferenceById(id1))
                .setOperation(PartChangeEventType.UPDATED_NAME)
                .setUser("YURY_KLIMCHUK")
                .setUpdate(LocalDate.now())
                .setObject(part1.toString())
                .setKey(key11);
        partChangeRepository.save(partChange11);
        em.flush();


        assertEquals(2, partChangeRepository.findAll(QDBPartChange.dBPartChange.part.number.eq(id1)).spliterator().estimateSize());

    }

}