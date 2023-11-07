package com.hydroyura.prodms.archive.services.parts;

import com.hydroyura.prodms.archive.dto.DTOPart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PartServiceTest {

    @Autowired @Qualifier(value = "PartService")
    private IPartService<DTOPart, String> partService;


    @Autowired
    private ApplicationContext context;

    @Test
    void context() {
        assertNotNull(partService);
    }

    @Test
    void getItemById() {
    }
}