package com.hydroyura.prodms.archive.dto;

import com.hydroyura.prodms.archive.data.entities.DBPart;
import com.hydroyura.prodms.archive.data.entities.DBPartStatus;
import com.hydroyura.prodms.archive.data.entities.DBPartType;
import com.hydroyura.prodms.archive.data.entities.DBRate;
import com.hydroyura.prodms.archive.data.repositories.BaseRepository;
import com.hydroyura.prodms.archive.services.changes.IPartChangeService;
import com.hydroyura.prodms.archive.services.parts.IPartService;
import com.hydroyura.prodms.archive.services.parts.PartService;
import com.hydroyura.prodms.archive.services.rates.IRateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DTOPartChangeTest {


    @Autowired
    IRateService rateService;

    @Autowired
    IPartService partService;


    @Test
    void mappingTest() {

        String assemblyNum = "0.0.0.023 СБ";
        String partNum = "0.0.0.123";
        DTOPart assembly = createPart(assemblyNum, DBPartStatus.TEST, DBPartType.ASSEMBLY);
        DTOPart part = createPart(partNum, DBPartStatus.TEST, DBPartType.PART);
        partService.create(assembly);
        partService.create(part);
        rateService.create(assemblyNum, partNum, 3L);

        String newNum = rateService.getAssemblies(partNum).stream().findAny().get().getAssembly().getNumber();
        assertEquals(assemblyNum, newNum);

    }

    DTOPart createPart(String number, DBPartStatus status, DBPartType type) {
        DTOPart part = new DTOPart();
        part.setVersion(1L);
        part.setCreated(LocalDate.now());
        part.setUpdated(LocalDate.now());
        part.setPdf(null);
        part.setOtherFile(null);
        part.setType(type);
        part.setStatus(status);
        part.setInfo(null);
        part.setName("Ось");
        part.setNumber(number);
        return part;
    }



}