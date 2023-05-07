package com.hydroyura.prodms.archive.services.changes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PartChangeServiceTest {

    @Autowired @Qualifier("PartChangeService")
    IPartChangeService service;

    @Test
    void getChanges() {
        service.getChanges("RGR100-666");
    }
}