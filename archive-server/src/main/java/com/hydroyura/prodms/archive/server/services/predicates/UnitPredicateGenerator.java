package com.hydroyura.prodms.archive.server.services.predicates;

import com.hydroyura.prodms.archive.client.dtos.unit.filter.FilterUnit;
import com.hydroyura.prodms.archive.server.services.predicates.PredicateGenerator;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UnitPredicateGenerator implements PredicateGenerator<FilterUnit> {

    @Override
    public Optional<Predicate> generate(FilterUnit filter) {
        return Optional.empty();
    }

}
