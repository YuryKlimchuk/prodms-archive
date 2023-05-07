package com.hydroyura.prodms.archive.services.objectcomparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Component(value = "ObjectComparator")
public class ObjectComparator implements IObjectComparator {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public <T> Map<String, String> getDifference(Class<T> clazz, T currentObj, T newObj) {
        logger.info("Attempt to find difference between objects of class: " + clazz.getName());
        Map<String, String> result = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();

        try {
            for (Field field : fields) {
                field.setAccessible(true);

                String str1 = String.valueOf(field.get(currentObj));
                String str2 = String.valueOf(field.get(newObj));

                if(!str1.equals(str2)) result.put(field.getName(), str2);
            }
        } catch (Exception e) {
            logger.warn("Error has occurred while comparing two objects of class: " + clazz.getName());
            e.printStackTrace();
        }
        return result;
    }
}
