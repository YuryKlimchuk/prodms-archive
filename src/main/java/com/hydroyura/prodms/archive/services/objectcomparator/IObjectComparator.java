package com.hydroyura.prodms.archive.services.objectcomparator;

import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public interface IObjectComparator {
    <T> Map<String, String> getDifference(Class<T> clazz, T currentObj, T newObj);

}
