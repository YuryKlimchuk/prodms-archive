package com.hydroyura.prodms.archive.services.rates.predicates;

import com.querydsl.core.types.Predicate;

import java.util.Map;

public interface IPredicateGenerator{

    public Predicate generate(Map<String, String> params);

}
