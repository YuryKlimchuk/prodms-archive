package com.hydroyura.prodms.archive;

import com.hydroyura.prodms.archive.data.entities.enums.DBPartStatus;
import com.hydroyura.prodms.archive.data.entities.enums.DBPartType;
import com.hydroyura.prodms.archive.data.entities.dto.DTOPart;
import com.hydroyura.prodms.archive.data.repositories.PartRepository;
import com.hydroyura.prodms.archive.services.parts.IPartService;
import com.hydroyura.prodms.archive.services.rates.IRateService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

@Component
public class TestData {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired @Qualifier(value = "PartService")
    private IPartService<DTOPart, String> partService;

    @Autowired @Qualifier(value = "RateService")
    private IRateService rateService;

    @Autowired
    private PartRepository partRepository;

    @PostConstruct
    void populate() {
        if (partRepository.findAll().size() > 0) return;
        try {
            // Детали
            partService.create(createPart("RGR100-00001", "Шпилька"));
            partService.create(createPart("RGR100-00001-01", "Шпилька"));
            partService.create(createPart("RGR100-00001-02", "Шпилька"));
            // 3
            partService.create(createPart("RGR100-10001", "Стакан"));
            partService.create(createPart("RGR100-10002", "Толкатель"));
            partService.create(createPart("RGR100-10005", "Пыльник"));
            // 6
            partService.create(createPart("RGR100-11001", "Корпус секции"));
            partService.create(createPart("RGR100-11001-01", "Корпус секции"));
            partService.create(createPart("RGR100-11001-02", "Корпус секции"));
            partService.create(createPart("RGR100-11002", "Золотник"));
            partService.create(createPart("RGR100-11002-01", "Золотник"));
            partService.create(createPart("RGR100-11002-02", "Золотник"));
            partService.create(createPart("RGR100-11003", "Корпус"));
            partService.create(createPart("RGR100-11004", "Клапан"));
            partService.create(createPart("RGR100-11005", "Пружина"));
            partService.create(createPart("RGR100-11006", "Стакан"));
            partService.create(createPart("RGR100-11007", "Пружина"));
            partService.create(createPart("RGR100-11008", "Стакан"));
            partService.create(createPart("RGR100-11009", "Шайба"));
            partService.create(createPart("RGR100-11009-01", "Шайба"));
            partService.create(createPart("RGR100-11010", "Фланец"));
            // 21
            partService.create(createPart("RGR100-16001", "Заглушка"));
            // 22
            partService.create(createPart("RGR100-20001", "Крышка сливная"));
            partService.create(createPart("RGR100-20001-01", "Крышка сливная"));
            partService.create(createPart("RGR100-20001-02", "Крышка сливная"));
            partService.create(createPart("RGR100-20002", "Штуцер"));
            // 26
            partService.create(createPart("RGR100-21001", "Заглушка"));
            // 27
            partService.create(createPart("RGR100-25001", "Крышка нагнетательная"));
            partService.create(createPart("RGR100-25001-01", "Крышка нагнетательная"));
            partService.create(createPart("RGR100-25001-02", "Крышка нагнетательная"));
            // 30
            partService.create(createPart("RGR100-26001", "Заглушка"));
            partService.create(createPart("RGR100-26001-01", "Заглушка"));
            // 32
            partService.create(createPart("KPD220/100-00001", "Корпус"));
            partService.create(createPart("KPD220/100-00002", "Клапан"));
            partService.create(createPart("KPD220/100-00003", "Пружина"));
            // 35


            // Сборки
            partService.create(createAssembly("RGR100/7-00000 СБ", "Распределитель"));
            partService.create(createAssembly("RGR100/6-00000 СБ", "Распределитель"));
            partService.create(createAssembly("RGR100/5-00000 СБ", "Распределитель"));
            // 3
            partService.create(createAssembly("RGR100-10000 СБ", "Секция"));
            partService.create(createAssembly("RGR100-10000-01 СБ", "Секция"));
            // 5
            partService.create(createAssembly("RGR100-15000 СБ", "Секция"));
            partService.create(createAssembly("RGR100-15000-01 СБ", "Секция"));
            // 7
            partService.create(createAssembly("RGR100-13000 СБ", "Секция"));
            partService.create(createAssembly("RGR100-13000-01 СБ", "Секция"));
            // 9
            partService.create(createAssembly("RGR100-16000 СБ", "Зашлушка")); // +
            // 10
            partService.create(createAssembly("RGR100-20000 СБ", "Крышка сливная")); // +
            partService.create(createAssembly("RGR100-20000-01 СБ", "Крышка сливная"));
            partService.create(createAssembly("RGR100-20000-02 СБ", "Крышка сливная"));
            // 13
            partService.create(createAssembly("RGR100-21000 СБ", "Зашлушка")); // +
            // 14
            partService.create(createAssembly("RGR100-25000 СБ", "Крышка нагнетательная"));
            partService.create(createAssembly("RGR100-25000-01 СБ", "Крышка нагнетательная"));
            partService.create(createAssembly("RGR100-25000-02 СБ", "Крышка нагнетательная"));
            // 17
            partService.create(createAssembly("RGR100-26000 СБ", "Зашлушка")); // +
            partService.create(createAssembly("RGR100-26000-01 СБ", "Зашлушка")); // +
            // 19
            partService.create(createAssembly("KPD220/100-00000 СБ", "Клапан предохранительный")); // +
            // 20


            // Стандартные изделия
            partService.create(createStandard("OR.000.001", "Кольцо уплотнительное"));
            partService.create(createStandard("OR.000.002", "Кольцо уплотнительное"));
            partService.create(createStandard("OR.000.003", "Кольцо уплотнительное"));
            partService.create(createStandard("OR.000.004", "Кольцо уплотнительное"));
            partService.create(createStandard("OR.000.005", "Кольцо уплотнительное"));
            // 5
            partService.create(createStandard("SCR.000.001", "Винт"));
            partService.create(createStandard("SCR.000.002", "Винт"));
            partService.create(createStandard("SCR.000.003", "Винт"));
            // 8
            partService.create(createStandard("N.000.001", "Гайка"));
            // 9
            partService.create(createStandard("GR.000.001", "Гровер"));
            // 10
            partService.create(createStandard("VC.000.001", "Клапан комбинированный"));
            partService.create(createStandard("VC.000.002", "Клапан комбинированный"));
            // 12

            /*
            // RGR100-16000 СБ
            rateService.create("RGR100-16000 СБ", "RGR100-16001", 1);
            rateService.create("RGR100-16000 СБ", "OR.000.001", 1);
            rateService.create("RGR100-16000 СБ", "OR.000.005", 1);
            // RGR100-21000 СБ
            rateService.create("RGR100-21000 СБ", "RGR100-21001", 1);
            rateService.create("RGR100-21000 СБ", "OR.000.005", 1);
            // RGR100-26000 СБ
            rateService.create("RGR100-26000 СБ", "RGR100-26001", 1);
            rateService.create("RGR100-26000 СБ", "OR.000.002", 1);
            rateService.create("RGR100-26000 СБ", "OR.000.005", 1);
            // RGR100-26000-01 СБ
            rateService.create("RGR100-26000-01 СБ", "RGR100-26001-01", 1);
            rateService.create("RGR100-26000-01 СБ", "OR.000.002", 1);
            rateService.create("RGR100-26000-01 СБ", "OR.000.005", 1);
            // KPD220/100-00000 СБ
            rateService.create("KPD220/100-00000 СБ", "KPD220/100-00001", 1);
            rateService.create("KPD220/100-00000 СБ", "KPD220/100-00002", 1);
            rateService.create("KPD220/100-00000 СБ", "KPD220/100-00003", 1);
            rateService.create("KPD220/100-00000 СБ", "RGR100-16000 СБ", 1);
            rateService.create("KPD220/100-00000 СБ", "OR.000.002", 2);
            rateService.create("KPD220/100-00000 СБ", "OR.000.005", 1);
            // RGR100-20000 СБ
            rateService.create("RGR100-20000 СБ", "RGR100-20001", 1);
            rateService.create("RGR100-20000 СБ", "RGR100-21000 СБ", 2);
            // RGR100-20000-01 СБ
            rateService.create("RGR100-20000-01 СБ", "RGR100-20001-01", 1);
            rateService.create("RGR100-20000-01 СБ", "RGR100-21000 СБ", 2);
            // RGR100-20000-02 СБ
            rateService.create("RGR100-20000-02 СБ", "RGR100-20001-02", 1);
            rateService.create("RGR100-20000-02 СБ", "RGR100-21000 СБ", 2);
            rateService.create("RGR100-20000-02 СБ", "RGR100-20002", 1);
            // RGR100-RGR100-25000 СБ
            rateService.create("RGR100-25000 СБ", "RGR100-25001", 1);
            rateService.create("RGR100-25000 СБ", "RGR100-26000 СБ", 1);
            rateService.create("RGR100-25000 СБ", "KPD220/100-00000 СБ", 1);
            // RGR100-RGR100-25000-01 СБ
            rateService.create("RGR100-25000-01 СБ", "RGR100-25001-01", 1);
            rateService.create("RGR100-25000-01 СБ", "RGR100-26000-01 СБ", 1);
            rateService.create("RGR100-25000-01 СБ", "KPD220/100-00000 СБ", 1);
            // RGR100-25000-02 СБ
            rateService.create("RGR100-25000-02 СБ", "RGR100-25001-02", 1);
            rateService.create("RGR100-25000-02 СБ", "RGR100-26000 СБ", 2);
            // RGR100-10000 СБ
            rateService.create("RGR100-10000 СБ", "RGR100-11001", 1);
            rateService.create("RGR100-10000 СБ", "RGR100-11002", 1);
            rateService.create("RGR100-10000 СБ", "RGR100-11003", 1);
            rateService.create("RGR100-10000 СБ", "RGR100-11004", 1);
            rateService.create("RGR100-10000 СБ", "RGR100-11005", 1);
            rateService.create("RGR100-10000 СБ", "RGR100-11006", 1);
            rateService.create("RGR100-10000 СБ", "RGR100-11007", 1);
            rateService.create("RGR100-10000 СБ", "RGR100-11008", 1);
            rateService.create("RGR100-10000 СБ", "RGR100-11009", 2);
            rateService.create("RGR100-10000 СБ", "OR.000.001", 1);
            rateService.create("RGR100-10000 СБ", "OR.000.005", 2);
            rateService.create("RGR100-10000 СБ", "SCR.000.001", 8);
            // RGR100-10000-01 СБ
            rateService.create("RGR100-10000-01 СБ", "RGR100-11001-01", 1);
            rateService.create("RGR100-10000-01 СБ", "RGR100-11002", 1);
            rateService.create("RGR100-10000-01 СБ", "RGR100-11003", 1);
            rateService.create("RGR100-10000-01 СБ", "RGR100-11004", 1);
            rateService.create("RGR100-10000-01 СБ", "RGR100-11005", 1);
            rateService.create("RGR100-10000-01 СБ", "RGR100-11006", 1);
            rateService.create("RGR100-10000-01 СБ", "RGR100-11007", 1);
            rateService.create("RGR100-10000-01 СБ", "RGR100-11008", 1);
            rateService.create("RGR100-10000-01 СБ", "RGR100-11009", 2);
            rateService.create("RGR100-10000-01 СБ", "OR.000.001", 1);
            rateService.create("RGR100-10000-01 СБ", "OR.000.005", 2);
            rateService.create("RGR100-10000-01 СБ", "SCR.000.001", 8);
            rateService.create("RGR100-10000-01 СБ", "VC.000.001", 2);
            // RGR100-13000 СБ
            rateService.create("RGR100-13000 СБ", "RGR100-11001-02", 1);
            rateService.create("RGR100-13000 СБ", "RGR100-11002-02", 1);
            rateService.create("RGR100-13000 СБ", "RGR100-11003", 1);
            rateService.create("RGR100-13000 СБ", "RGR100-11004", 1);
            rateService.create("RGR100-13000 СБ", "RGR100-11005", 1);
            rateService.create("RGR100-13000 СБ", "RGR100-11006", 1);
            rateService.create("RGR100-13000 СБ", "RGR100-11007", 1);
            rateService.create("RGR100-13000 СБ", "RGR100-11008", 1);
            rateService.create("RGR100-13000 СБ", "RGR100-11009-01", 2);
            rateService.create("RGR100-13000 СБ", "OR.000.001", 1);
            rateService.create("RGR100-13000 СБ", "OR.000.005", 2);
            rateService.create("RGR100-13000 СБ", "SCR.000.001", 8);
            // RGR100-13000-01 СБ
            rateService.create("RGR100-13000-01 СБ", "RGR100-11001-01", 1);
            rateService.create("RGR100-13000-01 СБ", "RGR100-11002-01", 1);
            rateService.create("RGR100-13000-01 СБ", "RGR100-11003", 1);
            rateService.create("RGR100-13000-01 СБ", "RGR100-11004", 1);
            rateService.create("RGR100-13000-01 СБ", "RGR100-11005", 1);
            rateService.create("RGR100-13000-01 СБ", "RGR100-11006", 1);
            rateService.create("RGR100-13000-01 СБ", "RGR100-11007", 1);
            rateService.create("RGR100-13000-01 СБ", "RGR100-11008", 1);
            rateService.create("RGR100-13000-01 СБ", "RGR100-11009-01", 2);
            rateService.create("RGR100-13000-01 СБ", "OR.000.001", 1);
            rateService.create("RGR100-13000-01 СБ", "OR.000.005", 2);
            rateService.create("RGR100-13000-01 СБ", "SCR.000.001", 8);
            rateService.create("RGR100-13000-01 СБ", "RGR100-16000 СБ", 1);
            rateService.create("RGR100-13000-01 СБ", "VC.000.001", 1);
            // RGR100-15000 СБ
            rateService.create("RGR100-15000 СБ", "RGR100-11001-02", 1);
            rateService.create("RGR100-15000 СБ", "RGR100-11001-01", 1);
            rateService.create("RGR100-15000 СБ", "RGR100-11002-02", 1);
            rateService.create("RGR100-15000 СБ", "RGR100-11002", 2);
            rateService.create("RGR100-15000 СБ", "RGR100-11003", 2);
            rateService.create("RGR100-15000 СБ", "RGR100-11004", 2);
            rateService.create("RGR100-15000 СБ", "RGR100-11005", 2);
            rateService.create("RGR100-15000 СБ", "RGR100-11006", 2);
            rateService.create("RGR100-15000 СБ", "RGR100-11007", 2);
            rateService.create("RGR100-15000 СБ", "RGR100-11008", 2);
            rateService.create("RGR100-15000 СБ", "RGR100-11009-01", 4);
            rateService.create("RGR100-15000 СБ", "OR.000.001", 2);
            rateService.create("RGR100-15000 СБ", "OR.000.005", 4);
            rateService.create("RGR100-15000 СБ", "SCR.000.001", 16);
            // RGR100-15000-01 СБ
            rateService.create("RGR100-15000-01 СБ", "RGR100-11001-01", 2);
            rateService.create("RGR100-15000-01 СБ", "RGR100-11002-02", 2);
            rateService.create("RGR100-15000-01 СБ", "RGR100-11003", 2);
            rateService.create("RGR100-15000-01 СБ", "RGR100-11004", 2);
            rateService.create("RGR100-15000-01 СБ", "RGR100-11005", 2);
            rateService.create("RGR100-15000-01 СБ", "RGR100-11006", 2);
            rateService.create("RGR100-15000-01 СБ", "RGR100-11007", 2);
            rateService.create("RGR100-15000-01 СБ", "RGR100-11010", 2);
            rateService.create("RGR100-15000-01 СБ", "RGR100-11009-01", 4);
            rateService.create("RGR100-15000-01 СБ", "OR.000.001", 2);
            rateService.create("RGR100-15000-01 СБ", "OR.000.005", 4);
            rateService.create("RGR100-15000-01 СБ", "SCR.000.001", 16);
            rateService.create("RGR100-15000-01 СБ", "VC.000.002", 4);
            // RGR100/7-00000 СБ
            rateService.create("RGR100/7-00000 СБ", "RGR100-00001", 4);
            rateService.create("RGR100/7-00000 СБ", "N.000.001", 8);
            rateService.create("RGR100/7-00000 СБ", "GR.000.001", 8);
            rateService.create("RGR100/7-00000 СБ", "OR.000.003", 8);
            rateService.create("RGR100/7-00000 СБ", "OR.000.004", 16);
            rateService.create("RGR100/7-00000 СБ", "OR.000.005", 8);
            rateService.create("RGR100/7-00000 СБ", "RGR100-25000 СБ", 1);
            rateService.create("RGR100/7-00000 СБ", "RGR100-20000 СБ", 1);
            rateService.create("RGR100/7-00000 СБ", "RGR100-10000 СБ", 2);
            rateService.create("RGR100/7-00000 СБ", "RGR100-10000-01 СБ", 1);
            rateService.create("RGR100/7-00000 СБ", "RGR100-15000 СБ", 1);
            rateService.create("RGR100/7-00000 СБ", "RGR100-15000-01 СБ", 1);
            // RGR100/6-00000 СБ
            rateService.create("RGR100/6-00000 СБ", "RGR100-00001-01", 4);
            rateService.create("RGR100/6-00000 СБ", "N.000.001", 8);
            rateService.create("RGR100/6-00000 СБ", "GR.000.001", 8);
            rateService.create("RGR100/6-00000 СБ", "OR.000.003", 7);
            rateService.create("RGR100/6-00000 СБ", "OR.000.004", 14);
            rateService.create("RGR100/6-00000 СБ", "OR.000.005", 7);
            rateService.create("RGR100/6-00000 СБ", "RGR100-25000-02 СБ", 1);
            rateService.create("RGR100/6-00000 СБ", "RGR100-20000-01 СБ", 1);
            rateService.create("RGR100/6-00000 СБ", "RGR100-13000 СБ", 1);
            rateService.create("RGR100/6-00000 СБ", "RGR100-10000-01 СБ", 1);
            rateService.create("RGR100/6-00000 СБ", "RGR100-15000 СБ", 2);
            // RGR100/5-00000 СБ
            rateService.create("RGR100/5-00000 СБ", "RGR100-00001-02", 4);
            rateService.create("RGR100/5-00000 СБ", "N.000.001", 8);
            rateService.create("RGR100/5-00000 СБ", "GR.000.001", 8);
            rateService.create("RGR100/5-00000 СБ", "OR.000.003", 6);
            rateService.create("RGR100/5-00000 СБ", "OR.000.004", 12);
            rateService.create("RGR100/5-00000 СБ", "OR.000.005", 6);
            rateService.create("RGR100/5-00000 СБ", "RGR100-25000-02 СБ", 1);
            rateService.create("RGR100/5-00000 СБ", "RGR100-20000-02 СБ", 1);
            rateService.create("RGR100/5-00000 СБ", "RGR100-13000-01 СБ", 4);
            rateService.create("RGR100/5-00000 СБ", "RGR100-10000 СБ", 1);
            */
        } catch (Exception e) {
            logger.warn(e.getMessage());
            logger.warn("Error while init database data");
        }
    }


    DTOPart createPart(String number, String name) {
        DTOPart part = new DTOPart();
        part.setName(name);
        part.setNumber(number);
        part.setType(DBPartType.PART);
        part.setStatus(DBPartStatus.DESIGN);
        return part;
    }

    DTOPart createAssembly(String number, String name) {
        DTOPart part = new DTOPart();
        part.setName(name);
        part.setNumber(number);
        part.setType(DBPartType.ASSEMBLY);
        part.setStatus(DBPartStatus.DESIGN);
        return part;
    }

    DTOPart createStandard(String number, String name) {
        DTOPart part = new DTOPart();
        part.setName(name);
        part.setNumber(number);
        part.setType(DBPartType.STANDARD_PART);
        part.setStatus(DBPartStatus.NONE);
        return part;
    }

}
