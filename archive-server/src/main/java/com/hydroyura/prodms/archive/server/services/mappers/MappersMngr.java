package com.hydroyura.prodms.archive.server.services.mappers;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MappersMngr {

    private Map<Map.Entry, BaseMapper> mappers = Collections.EMPTY_MAP;

    private final String ERROR_MSG = "Cannot find mapper with params: source = [%s], destination = [%s]";

    public MappersMngr(Collection<BaseMapper> _mappers) {
        mappers = _mappers
                .stream()
                .collect(Collectors.toMap(BaseMapper::getType, Function.identity()));
    }

    // TODO: custom exception
    public <Source, Destination> BaseMapper<Source, Destination> getMapper(Map.Entry<Class<Source>, Class<Destination>> key) {
        return Optional
                .ofNullable(mappers.get(key))
                .orElseThrow(() -> new RuntimeException(buildErrorMsg(key)));
    }

    private <Source, Destination> String buildErrorMsg(Map.Entry<Class<Source>, Class<Destination>> key) {
        return ERROR_MSG.formatted(key.getKey().getName(), key.getValue().getName());
    }

}
