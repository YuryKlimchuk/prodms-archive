package com.hydroyura.prodms.archive.server.repositories;

import com.querydsl.core.types.Predicate;

import java.util.Optional;

public interface PredicateGenerator<Filter> {

    Optional<Predicate> generate(Filter filter);

}
