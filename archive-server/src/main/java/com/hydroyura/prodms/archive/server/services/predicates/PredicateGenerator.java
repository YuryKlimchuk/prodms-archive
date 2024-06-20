package com.hydroyura.prodms.archive.server.services.predicates;

import com.querydsl.core.types.Predicate;

import java.util.Optional;

public interface PredicateGenerator<Filter> {

    Optional<Predicate> generate(Filter filter);

}
