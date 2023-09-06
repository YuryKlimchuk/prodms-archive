package com.hydroyura.prodms.archive;

import com.hydroyura.prodms.archive.data.entities.DBPartStatus;
import com.hydroyura.prodms.archive.data.entities.DBPartType;
import com.hydroyura.prodms.archive.dto.DTOPart;
import com.hydroyura.prodms.archive.services.parts.IPartService;
import com.hydroyura.prodms.archive.services.rates.IRateService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

@Component
public class TestData {

    @Autowired @Qualifier(value = "PartService")
    private IPartService<DTOPart, String> partService;

    @Autowired @Qualifier(value = "RateService")
    private IRateService rateService;


    @Autowired
    WebApplicationContext context;

    @Autowired
    ApplicationContext context2;





    @PostConstruct
    void populate() {
        try {
            partService.create(createPart("00.000.000.001", "Золотник"));
            partService.create(createPart("00.000.000.002", "Корпус"));
            partService.create(createPart("00.000.000.003", "Направляющая"));
            partService.create(createPart("00.000.000.004", "Пробка"));
            partService.create(createPart("00.000.000.005", "Пружина"));
            partService.create(createPart("00.000.000.006", "Винт"));
            partService.create(createPart("00.000.000.007", "Направляющая"));
            partService.create(createPart("00.000.000.008", "Пружина"));

            partService.create(createPart("00.000.001.001", "Корпус"));

            partService.create(createStandard("B.000.001", "Шарик"));
            partService.create(createStandard("B.000.002", "Шарик"));
            partService.create(createStandard("GA.000.001", "Пневмогидроаккумулятор"));
            partService.create(createStandard("N.000.001", "Гайка"));
            partService.create(createStandard("RR.001.002", "Кольцо стопорное"));
            partService.create(createStandard("RR.000.003", "Кольцо стопорное"));
            partService.create(createStandard("WRM.000.001", "Шайба резинометаллическая"));
            partService.create(createStandard("OR.000.001", "Кольцо резиновое"));
            partService.create(createStandard("OR.000.002", "Кольцо резиновое"));

            partService.create(createAssembly("00.000.001.000 СБ", "Клапан обратный"));
            partService.create(createAssembly("00.000.000.000 СБ", "Блок питания"));

            // ---------------------------------------------------------------------------------------------

            // --------------------------- Rates:: 00.000.001.000 СБ ---------------------------------------
            rateService.create("00.000.001.000 СБ", "RR.001.002", 1);
            rateService.create("00.000.001.000 СБ", "OR.000.002", 1);
            rateService.create("00.000.001.000 СБ", "00.000.001.001", 1);
            rateService.create("00.000.001.000 СБ", "B.000.001", 1);
            // --------------------------- Rates:: 00.000.000.000 СБ ---------------------------------------
            rateService.create("00.000.000.000 СБ", "00.000.001.000 СБ", 2);
            rateService.create("00.000.000.000 СБ", "B.000.001", 1);
            rateService.create("00.000.000.000 СБ", "B.000.002", 1);
            rateService.create("00.000.000.000 СБ", "GA.000.001", 1);
            rateService.create("00.000.000.000 СБ", "N.000.001", 1);
            rateService.create("00.000.000.000 СБ", "WRM.000.001", 1);
            rateService.create("00.000.000.000 СБ", "OR.000.001", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    DTOPart createPart(String number, String name) {
        DTOPart part = new DTOPart();
        part.setName(name);
        part.setNumber(number);
        part.setUpdated(LocalDate.now());
        part.setCreated(LocalDate.now());
        part.setType(DBPartType.PART);
        part.setStatus(DBPartStatus.DESIGN);
        part.setVersion(1);
        part.setPdf("NONE");
        part.setOtherFile("NONE");
        part.setInfo(null);
        return part;
    }

    DTOPart createAssembly(String number, String name) {
        DTOPart part = new DTOPart();
        part.setName(name);
        part.setNumber(number);
        part.setUpdated(LocalDate.now());
        part.setCreated(LocalDate.now());
        part.setType(DBPartType.ASSEMBLY);
        part.setStatus(DBPartStatus.DESIGN);
        part.setVersion(1);
        part.setPdf("NONE");
        part.setOtherFile("NONE");
        part.setInfo(null);
        return part;
    }

    DTOPart createStandard(String number, String name) {
        DTOPart part = new DTOPart();
        part.setName(name);
        part.setNumber(number);
        part.setUpdated(LocalDate.now());
        part.setCreated(LocalDate.now());
        part.setType(DBPartType.STANDARD_PART);
        part.setStatus(DBPartStatus.NONE);
        part.setVersion(1);
        part.setPdf("NONE");
        part.setOtherFile("NONE");
        part.setInfo(null);
        return part;
    }

}
