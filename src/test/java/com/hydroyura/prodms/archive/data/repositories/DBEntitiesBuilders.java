package com.hydroyura.prodms.archive.data.repositories;

import com.hydroyura.prodms.archive.data.entities.*;
import com.hydroyura.prodms.archive.data.entities.keys.DBRateKey;

import java.time.LocalDate;

public class DBEntitiesBuilders {

    public static DBPart createDBPart() {
        DBPart part = new DBPart();

        part
            .setNumber("TEST_NUMBER")
            .setName("TEST_NAME")
            .setStatus(DBPartStatus.DESIGN)
            .setType(DBPartType.PART);
        return part;
    }

    public static DBPart createDBPart(String number, String name, DBPartStatus status, DBPartType type) {
        DBPart part = new DBPart();

        part
                .setNumber(number)
                .setName(name)
                .setStatus(status)
                .setType(type);
        return part;
    }

    public static DBPartChange createDBPartChange(DBPart part) {
        DBPartChange partChange = new DBPartChange();

        partChange
                .setKey(new DBPartChangeKey().setPartNumber(part.getNumber()).setVersion(1L))
                .setPart(part)
                .setFieldName("TEST_FIELD")
                .setFieldValue("TEST_VALUE")
                .setUser("YURY")
                .setObject("JSON")
                .setUpdate(LocalDate.now())
                .setOperation("CREATE");

        return partChange;
    }

    public static DBRate createDBRate(DBPart assembly, DBPart element) {
        DBRateKey key = new DBRateKey();
        key.setAssemblyId(assembly.getNumber());
        key.setElementId(element.getNumber());

        DBRate rate = new DBRate();
        rate.setAssembly(assembly);
        rate.setElement(element);
        rate.setKey(key);
        rate.setCount(2L);

        return rate;
    }
}
