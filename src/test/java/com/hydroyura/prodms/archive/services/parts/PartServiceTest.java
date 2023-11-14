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

import java.util.Collection;
import java.util.Optional;

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



    private DTOPart create(String id) {
        return new DTOPart()
                .setName("NAME")
                .setNumber(id)
                .setStatus(DBPartStatus.TEST)
                .setType(DBPartType.PART);
    }

}
