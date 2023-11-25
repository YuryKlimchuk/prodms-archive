package com.hydroyura.prodms.archive.data.repositories;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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

}