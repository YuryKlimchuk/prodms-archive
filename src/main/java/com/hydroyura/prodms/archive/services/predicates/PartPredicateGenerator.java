package com.hydroyura.prodms.archive.services.predicates;

import com.hydroyura.prodms.archive.data.entities.DBPartStatus;
import com.hydroyura.prodms.archive.data.entities.DBPartType;
import com.hydroyura.prodms.archive.data.entities.QDBPart;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component(value = "PartPredicateGenerator")
public class PartPredicateGenerator implements IPredicateGenerator {

    @Override
    public Predicate generate(Map<String, String> params) {
        if (params == null || params.isEmpty()) return Expressions.TRUE;
        Collection<Predicate> predicates = new ArrayList<>();

        Optional<String> type = Optional.ofNullable(params.get("TYPE"));
        type.ifPresent(value -> predicates.add(QDBPart.dBPart.type.eq(DBPartType.valueOf(value))));

        Optional<String> status = Optional.ofNullable(params.get("STATUS"));
        status.ifPresent(value -> {
            String[] values = value.split(",");
            predicates.add(QDBPart.dBPart.status.in(
                    Arrays.stream(values)
                          .map(DBPartStatus::valueOf)
                          .collect(Collectors.toList()))
            );
        });

        Optional<String> name = Optional.ofNullable(params.get("NAME"));
        name.ifPresent(value -> predicates.add(QDBPart.dBPart.name.contains(value)));

        Optional<String> number = Optional.ofNullable(params.get("NUMBER"));
        number.ifPresent(value -> predicates.add(QDBPart.dBPart.number.contains(value)));

        if(predicates.isEmpty()) return Expressions.TRUE;
        return ExpressionUtils.allOf(predicates);
    }
}
