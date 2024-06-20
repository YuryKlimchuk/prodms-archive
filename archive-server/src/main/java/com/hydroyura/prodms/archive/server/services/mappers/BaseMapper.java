package com.hydroyura.prodms.archive.server.services.mappers;

import org.springframework.data.util.Pair;

import java.util.Map;

public interface BaseMapper <Source, Destination> {
    Destination sourceToDestination(Source source);
    Source destinationToSource(Destination destination);
    Map.Entry<Class<Source>, Class<Destination>> getType();
}
