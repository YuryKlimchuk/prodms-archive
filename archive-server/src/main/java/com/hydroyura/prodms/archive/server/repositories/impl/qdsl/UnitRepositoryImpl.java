package com.hydroyura.prodms.archive.server.repositories.impl.qdsl;

import com.hydroyura.prodms.archive.client.dtos.unit.filter.FilterUnit;
import com.hydroyura.prodms.archive.server.entities.QUnit;
import com.hydroyura.prodms.archive.server.entities.Unit;
import com.hydroyura.prodms.archive.server.repositories.UnitRepository;
import com.hydroyura.prodms.archive.server.repositories.PredicateGenerator;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Optional;

@Repository
@Transactional
public class UnitRepositoryImpl implements UnitRepository {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;
    private QUnit qunit = QUnit.unit;
    private JPAQueryFactory queryFactory;


    @Autowired
    private PredicateGenerator<FilterUnit> predicateGenerator;

    private Predicate ALWAYS_TRUE = Expressions.TRUE.eq(true);

    private Boolean LOG_INFO = Boolean.TRUE;
    private String DELETED_STATUS = "DELETED";


    @PostConstruct
    void buildQueryFactory() {
        queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, em);
    }

    @Override
    public Optional<String> create(Unit unit) {
        if (LOG_INFO) {
            LOG.info("Attempt to create new unit with number = [{}]", unit.getNumber());
        }
        ZonedDateTime zonedNow = ZonedDateTime.now(ZoneId.of("UTC"));
        unit.setCreated(zonedNow);
        unit.setUpdated(zonedNow);
        unit.setVersion(1);
        em.persist(unit);
        em.flush();
        return Optional.ofNullable(unit.getNumber());
    }

    @Override
    public Optional<Unit> findOne(String number) {
        Unit result = queryFactory
                .selectFrom(qunit)
                .where(qunit.number.eq(number).and(qunit.status.ne(DELETED_STATUS)))
                .fetchOne();
        return Optional.ofNullable(result);
    }

    @Override
    public Boolean delete(String number) {
        if (findOne(number).isEmpty()) {
            return Boolean.FALSE;
        }
        queryFactory
                .update(qunit)
                .where(qunit.number.eq(number))
                .set(qunit.status, DELETED_STATUS)
                .execute();
        return Boolean.TRUE;
    }

    @Override
    public Boolean update(Unit unit) {
        Optional<Unit> result = findOne(unit.getNumber());
        if (result.isEmpty()) {
            return Boolean.FALSE;
        }
        ZonedDateTime zonedNow = ZonedDateTime.now(ZoneId.of("UTC"));
        queryFactory
                .update(qunit)
                .where(qunit.number.eq(unit.getNumber()))
                .set(qunit.name, unit.getName())
                .set(qunit.status, unit.getStatus())
                .set(qunit.updated, zonedNow)
                .set(qunit.version, result.get().getVersion() + 1)
                .set(qunit.comment, unit.getComment())
                .execute();
        return Boolean.TRUE;
    }

    @Override
    public Collection<Unit> findMany(FilterUnit filterUnit) {
        JPAQuery<Unit> query = queryFactory.selectFrom(qunit);
        predicateGenerator.generate(filterUnit).ifPresent(query::where);
        return query.fetch();
    }
}