package com.hydroyura.prodms.archive.services.predicates;

import com.hydroyura.prodms.archive.data.entities.QDBRate;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Component(value = "PartRatePredicateGenerator")
public class PartRatePredicateGenerator implements IPredicateGenerator {


    @Override
    public Predicate generate(Map<String, String> params) {
        if (params == null || params.isEmpty()) return Expressions.TRUE;
        Collection<Predicate> predicates = new ArrayList<>();

        Optional<String> assemblyNumber = Optional.ofNullable(params.get("assembly-number"));
        assemblyNumber.ifPresent(value -> predicates.add(QDBRate.dBRate.assembly.number.eq(value)));

        Optional<String> elementNumber = Optional.ofNullable(params.get("element-number"));
        elementNumber.ifPresent(value -> predicates.add(QDBRate.dBRate.element.number.eq(value)));

        if(predicates.isEmpty()) return Expressions.TRUE;
        return ExpressionUtils.allOf(predicates);
    }
}