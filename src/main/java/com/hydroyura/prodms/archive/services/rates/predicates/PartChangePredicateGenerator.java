package com.hydroyura.prodms.archive.services.rates.predicates;

import com.hydroyura.prodms.archive.data.entities.QDBPartChange;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.stereotype.Component;

import java.util.*;

@Component(value = "PartChangePredicateGenerator")
public class PartChangePredicateGenerator implements IPredicateGenerator {
    @Override
    public Predicate generate(Map<String, String> params) {
        if (params == null || params.isEmpty()) return Expressions.FALSE;
        Collection<Predicate> predicates = new ArrayList<>();

        Optional<String> number = Optional.ofNullable(params.get("NUMBER"));
        number.ifPresent(value -> predicates.add(QDBPartChange.dBPartChange.part.number.eq(value)));

        if(predicates.isEmpty()) return Expressions.FALSE;
        return ExpressionUtils.allOf(predicates);
    }

}
