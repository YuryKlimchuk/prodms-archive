package com.hydroyura.prodms.archive.server.repositories;

import com.hydroyura.prodms.archive.client.dtos.unit.filter.FilterUnit;
import com.hydroyura.prodms.archive.server.entities.QUnit;
import com.hydroyura.prodms.archive.server.entities.Unit;
import com.hydroyura.prodms.archive.server.repositories.impl.qdsl.UnitRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
/*
@SpringBootTest
@Testcontainers
class UnitRepositoryImplTest {

    @Autowired
    private UnitRepositoryImpl repository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15");

    private QUnit qunit = QUnit.unit;

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("postgresql.driver", postgreSQLContainer::getDriverClassName);
        registry.add("spring.flyway.enabled", () -> true);
        registry.add("spring.flyway.url", () -> postgreSQLContainer.getJdbcUrl());
        registry.add("spring.flyway.username", () -> postgreSQLContainer.getUsername());
        registry.add("spring.flyway.password", () -> postgreSQLContainer.getPassword());
        registry.add("spring.flyway.location", () -> "classpath:db/migration");
    }

    @Test
    void injection() {
        assertNotNull(repository);
    }

    @Test
    void createOne() {
        String number = UUID.randomUUID().toString();
        Unit unit = createUnit(number, "TEST_NAME", "TEST_TYPE", "TEST_STATUS");
        //assertEquals(number, repository.create(unit).orElse("ERROR"));
        assertThrowsExactly(DataIntegrityViolationException.class, () -> repository.create(unit));
    }

    @Test
    void createDuplication() {
        String number = UUID.randomUUID().toString();
        Unit unit = createUnit(number, "TEST_NAME", "TEST_TYPE", "TEST_STATUS");
        repository.create(unit);
        assertThrowsExactly(DataIntegrityViolationException.class, () -> repository.create(unit));
    }

    @Test
    void findOne() {
        String number = UUID.randomUUID().toString();
        Unit unit = createUnit(number, "TEST_NAME", "TEST_TYPE", "TEST_STATUS");
        repository.create(unit);
        assertEquals(Boolean.TRUE, repository.findOne(number).isPresent());
    }

    @Test
    void noFindOne() {
        String number = UUID.randomUUID().toString();
        assertEquals(Boolean.FALSE, repository.findOne(number).isPresent());
    }

    @Test
    void delete() {
        String number = UUID.randomUUID().toString();
        Unit unit = createUnit(number, "TEST_NAME", "TEST_TYPE", "TEST_STATUS");
        repository.create(unit);
        repository.delete(number);
        assertEquals(Boolean.FALSE, repository.findOne(number).isPresent());
    }

    @Test
    void noDelete() {
        String number = UUID.randomUUID().toString();
        assertEquals(Boolean.FALSE, repository.delete(number));
    }

    @Test
    void update() {
        String number = UUID.randomUUID().toString();
        Unit unit = createUnit(number, "TEST_NAME", "TEST_TYPE", "TEST_STATUS");
        repository.create(unit);

        String newName = "NEW_NAME";
        String newStatus = "NEW_STATUS";
        String newComment = "NEW_COMMENT";

        unit.setName(newName);
        unit.setStatus(newStatus);
        unit.setComment(newComment);

        repository.update(unit);
        Unit updatedUnit = repository.findOne(unit.getNumber()).get();
        assertEquals(newName, updatedUnit.getName());
        assertEquals(newStatus, updatedUnit.getStatus());
        assertEquals(newComment, updatedUnit.getComment());
        assertNotEquals(unit.getUpdated(), updatedUnit.getUpdated());
        assertEquals(2, updatedUnit.getVersion());
    }

    @Test
    void findMany() {
        String number1 = UUID.randomUUID().toString();
        String number2 = UUID.randomUUID().toString();
        String number3 = UUID.randomUUID().toString();
        Unit unit1 = createUnit(number1, "TEST_TEST_NAME1", "TEST_TEST_TYPE", "TEST_TEST_STATUS_1");
        Unit unit2 = createUnit(number2, "TEST_TEST_NAME2", "TEST_TEST_TYPE", "TEST_TEST_STATUS");
        Unit unit3 = createUnit(number3, "TEST_TEST_NAME3", "TEST_TEST_TYPE_1", "TEST_TEST_STATUS");
        repository.create(unit1);
        repository.create(unit2);
        repository.create(unit3);



        FilterUnit filter1 = new FilterUnit().setName("TEST_TEST_NAME");
        assertEquals(3, repository.findMany(filter1).size());

        FilterUnit filter2 = new FilterUnit();
        filter2.getStatuses().add("TEST_TEST_STATUS");
        assertEquals(2, repository.findMany(filter2).size());

        FilterUnit filter3 = new FilterUnit();
        filter3.getTypes().add("TEST_TEST_TYPE_1");
        assertEquals(1, repository.findMany(filter3).size());
    }




    private static Unit createUnit(String number, String name, String type, String status) {
        Unit unit = new Unit();
        unit.setName(name);
        unit.setNumber(number);
        unit.setStatus(status);
        unit.setType(type);
        return unit;
    }
}
*/