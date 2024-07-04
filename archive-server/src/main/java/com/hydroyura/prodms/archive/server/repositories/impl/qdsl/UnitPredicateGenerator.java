package com.hydroyura.prodms.archive.server.repositories.impl.qdsl;

import com.hydroyura.prodms.archive.client.dtos.unit.filter.FilterUnit;
import com.hydroyura.prodms.archive.server.entities.QUnit;
import com.hydroyura.prodms.archive.server.repositories.PredicateGenerator;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class UnitPredicateGenerator implements PredicateGenerator<FilterUnit> {

    private QUnit qUnit = QUnit.unit;

    // TODO: is it needed Optional here???
    @Override
    public Optional<Predicate> generate(FilterUnit filter) {
        Collection<Predicate> predicates = new ArrayList<>();

        Optional
                .ofNullable(filter.getName())
                .map(qUnit.name::like)
                .ifPresent(predicates::add);

        Optional
                .ofNullable(filter.getNumber())
                .map(qUnit.number::like)
                .ifPresent(predicates::add);

        if (!filter.getStatuses().isEmpty()) {
            predicates.add(qUnit.status.in(filter.getStatuses()));
        }

        if (!filter.getTypes().isEmpty()) {
            predicates.add(qUnit.type.in(filter.getTypes()));
        }

        if (predicates.isEmpty()) {
            return Optional.of(Expressions.TRUE);
        } else if (predicates.size() == 1) {
            return Optional.of(predicates.stream().findFirst().get());
        } else {
            return Optional.of(ExpressionUtils.allOf(predicates));
        }
    }

}
