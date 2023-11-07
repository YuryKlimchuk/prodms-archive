package com.hydroyura.prodms.archive.data.repositories;

import com.hydroyura.prodms.archive.data.entities.DBPart;
import com.hydroyura.prodms.archive.data.entities.DBPartChange;
import com.hydroyura.prodms.archive.data.entities.DBPartChangeKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PartChangeRepositoryTest {

    @Autowired @Qualifier(value = "PartChangeRepository")
    private BaseRepository<DBPartChange, DBPartChangeKey> partChangeRepository;

    @Autowired @Qualifier(value = "PartRepository")
    private BaseRepository<DBPart, String> partRepository;

    @Test
    void context() {
        assertNotNull(partChangeRepository);
    }

    @Test
    void create() {
        DBPart part = partRepository.save(DBEntitiesBuilders.createDBPart());
        DBPartChangeKey key = new DBPartChangeKey().setVersion(1L).setPartNumber(part.getNumber());
        partChangeRepository.save(DBEntitiesBuilders.createDBPartChange(part));

        assertEquals(1L, partChangeRepository.findAll().size());
    }



}