package com.hydroyura.prodms.archive.data.repositories;

import com.hydroyura.prodms.archive.data.entities.DBPart;
import com.hydroyura.prodms.archive.data.entities.DBPartStatus;
import com.hydroyura.prodms.archive.data.entities.DBPartType;
import com.hydroyura.prodms.archive.services.predicates.IPredicateGenerator;
import com.hydroyura.prodms.archive.services.predicates.PartPredicateGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Map;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Import(PartPredicateGenerator.class)
class PartRepositoryTest {

    @Autowired @Qualifier(value = "PartRepository")
    private BaseRepository<DBPart, String> partRepository;

    @Autowired @Qualifier(value = "PartPredicateGenerator")
    private IPredicateGenerator partPredicateGenerator;


    @Test
    void contextLoad() {
        assertNotNull(partRepository);
        assertNotNull(partPredicateGenerator);
    }
    
    @Test
    void save() {
        DBPart part1 = DBEntitiesBuilders.createDBPart("part1", "part1", DBPartStatus.DESIGN, DBPartType.PART);
        DBPart part2 = DBEntitiesBuilders.createDBPart("part2", "part2", DBPartStatus.DESIGN, DBPartType.PART);
        partRepository.save(part1);
        partRepository.save(part2);
        assertEquals(2, partRepository.findAll().size());
    }

    @Test
    void findByPredicate() {
        DBPart part1 = DBEntitiesBuilders.createDBPart("part1", "part1", DBPartStatus.DESIGN, DBPartType.PART);
        DBPart part2 = DBEntitiesBuilders.createDBPart("part2", "part2", DBPartStatus.DESIGN, DBPartType.PART);
        DBPart part3 = DBEntitiesBuilders.createDBPart("part3", "part3", DBPartStatus.PRODUCTION, DBPartType.ASSEMBLY);
        DBPart part4 = DBEntitiesBuilders.createDBPart("part4", "part4", DBPartStatus.PRODUCTION, DBPartType.ASSEMBLY);
        DBPart part5 = DBEntitiesBuilders.createDBPart("part5", "part5", DBPartStatus.TEST, DBPartType.STANDARD_PART);
        DBPart part6 = DBEntitiesBuilders.createDBPart("part6", "part6", DBPartStatus.TEST, DBPartType.BUY_PART);
        partRepository.save(part1);
        partRepository.save(part2);
        partRepository.save(part3);
        partRepository.save(part4);
        partRepository.save(part5);
        partRepository.save(part6);
        assertEquals(6, partRepository.findAll().size());

        Map<String, String> predicament1 = Map.of(
                "NUMBER", "part",
                "NAME", "5",
                "STATUS", "TEST"
        );
        assertEquals(1, StreamSupport.stream(partRepository.findAll(partPredicateGenerator.generate(predicament1)).spliterator(), false).count());
    }

}