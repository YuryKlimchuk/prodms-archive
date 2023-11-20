package com.hydroyura.prodms.archive.services.parts;

import com.hydroyura.prodms.archive.data.entities.DBPart;
import com.hydroyura.prodms.archive.data.entities.dto.DTOPart;
import com.hydroyura.prodms.archive.data.entities.dto.DTOPartChange;
import com.hydroyura.prodms.archive.data.entities.enums.DBPartStatus;
import com.hydroyura.prodms.archive.data.entities.enums.DBPartType;
import com.hydroyura.prodms.archive.services.changes.PartChangeService;
import com.hydroyura.prodms.archive.services.publisher.partchange.PartChangeEventType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@Import(PartServiceTestConfiguration.class)
@DataJpaTest
class PartServiceTest {

    @Autowired
    private PartService partService;

    @Autowired
    private PartChangeService partChangeService;

    @Autowired
    private EntityManager entityManager;

    @Test
    void inject() {
        assertNotNull(partService);
        assertNotNull(partChangeService);
    }

    @Test
    void create() {
        String id = "TEST_NUMBER";
        DTOPart part = create(id);
        partService.create(part);
        Collection<DTOPartChange> changes = partChangeService.getChanges(id);
        assertEquals(1L, changes.size());
        assertEquals(changes.stream().findFirst().get().getOperation(), PartChangeEventType.CREATED);

        // check creation with the same key
        entityManager.flush();
        DTOPart part1 = create(id);
        assertEquals(Optional.empty(), partService.create(part1));
    }

    @Test
    void getItemById() {
        String id = "TEST_NUMBER";
        DTOPart part = create(id);
        partService.create(part);
        assertEquals(id, partService.getItemById(id).get().getNumber());
        assertEquals(Optional.empty(), partService.getItemById("incorrect_id"));
    }

    @Test
    void delete() {
        String id = "TEST_NUMBER";
        DTOPart part = create(id);
        partService.create(part);
        entityManager.flush();
        partService.delete(id);
        entityManager.flush();
        assertEquals(id, partService.getItemById(id).get().getNumber());
        assertEquals(2, partChangeService.getChanges(id).size());
    }

    @Test
    void findByFilter() {
        partService.create(create("ID1", DBPartStatus.TEST, DBPartType.ASSEMBLY));
        partService.create(create("ID2", DBPartStatus.PRODUCTION, DBPartType.PART));
        partService.create(create("ID3", DBPartStatus.DESIGN, DBPartType.PART));
        partService.create(create("ID4", DBPartStatus.DESIGN, DBPartType.STANDARD_PART));
        partService.create(create("ID5", DBPartStatus.DESIGN, DBPartType.BUY_PART));
        entityManager.flush();

        Map<String, String> filter = new HashMap<>();
        filter.put("NUMBER", "ID");
        assertEquals(5, partService.getAllByFilter(filter).size());
        filter.put("TYPE", "PART");
        assertEquals(2, partService.getAllByFilter(filter).size());
        filter.put("STATUS", "PRODUCTION");
        assertEquals(1, partService.getAllByFilter(filter).size());
        filter.put("NAME", "NAME");
        assertEquals(1, partService.getAllByFilter(filter).size());
    }

    @Test
    void update() {
        DTOPart original = create("ID1", DBPartStatus.TEST, DBPartType.ASSEMBLY);
        partService.create(original);
        entityManager.flush();
        assertEquals(Optional.empty(), partService.update(original));

        original.setStatus(DBPartStatus.DESIGN);
        partService.update(original);
        entityManager.flush();
        assertEquals(
                PartChangeEventType.UPDATED_STATUS,
                partChangeService.getChanges("ID1").stream().collect(Collectors.toCollection(ArrayList::new)).get(1).getOperation()
        );

        original.setName("NEW_NAME");
        partService.update(original);
        entityManager.flush();
        assertEquals(
                PartChangeEventType.UPDATED_NAME,
                partChangeService.getChanges("ID1").stream().collect(Collectors.toCollection(ArrayList::new)).get(2).getOperation()
        );

        original.setPdf("NEW_PDF");
        partService.update(original);
        entityManager.flush();
        assertEquals(
                PartChangeEventType.UPDATED_PDF,
                partChangeService.getChanges("ID1").stream().collect(Collectors.toCollection(ArrayList::new)).get(3).getOperation()
        );

    }



    private DTOPart create(String id) {
        return new DTOPart()
                .setName("NAME")
                .setNumber(id)
                .setStatus(DBPartStatus.TEST)
                .setType(DBPartType.PART);
    }

    private DTOPart create(String id, DBPartStatus status, DBPartType type) {
        return new DTOPart()
                .setName("NAME")
                .setNumber(id)
                .setStatus(status)
                .setType(type);
    }

}
