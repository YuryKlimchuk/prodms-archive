package com.hydroyura.prodms.archive.server.db.repository;

import static com.hydroyura.prodms.archive.server.SharedConstants.EX_MSG_UNIT_DELETE;
import static com.hydroyura.prodms.archive.server.SharedConstants.LOG_MSG_UNIT_NOT_FOUND;

import com.hydroyura.prodms.archive.client.model.enums.EnumUtils;
import com.hydroyura.prodms.archive.client.model.req.ListUnitsReq;
import com.hydroyura.prodms.archive.server.db.EntityManagerProvider;
import com.hydroyura.prodms.archive.server.db.entity.Unit;
import com.hydroyura.prodms.archive.server.db.order.UnitOrder;
import com.hydroyura.prodms.archive.server.exception.model.db.UnitDeleteException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
@RequiredArgsConstructor
@Slf4j
public class UnitRepositoryImpl implements UnitRepository {

    private final EntityManagerProvider entityManagerProvider;

    @Override
    public void create(Unit unit) {
        entityManagerProvider.getEntityManager().persist(unit);
    }

    @Override
    public void create(List<Unit> units) {

    }

    @Override
    public Optional<Unit> get(String number) {
        try {
            CriteriaBuilder criteriaBuilder = entityManagerProvider.getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Unit> criteriaQuery = criteriaBuilder.createQuery(Unit.class);
            Root<Unit> root = criteriaQuery.from(Unit.class);

            Collection<Predicate> andPredicates = new ArrayList<>();
            andPredicates.add(criteriaBuilder.equal(root.get("number"), number));
            andPredicates.add(criteriaBuilder.equal(root.get("isActive"), Boolean.TRUE));

            criteriaQuery.where(criteriaBuilder.and(andPredicates.toArray(Predicate[]::new)));
            var unit = entityManagerProvider.getEntityManager().createQuery(criteriaQuery).getSingleResult();
            return Optional.of(unit);
        } catch (NoResultException ex) {
            log.warn(LOG_MSG_UNIT_NOT_FOUND, number);
            return Optional.empty();
        }
    }

    @Override
    public void delete(String number) {
        var unit = get(number)
            .map(u -> {
                u.setUpdatedAt(Instant.now().getEpochSecond());
                u.setVersion(u.getVersion() + 1);
                u.setIsActive(Boolean.FALSE);
                return u;
            });
        if (unit.isPresent()) {
            entityManagerProvider.getEntityManager().merge(unit.get());
        } else {
            throw new UnitDeleteException(EX_MSG_UNIT_DELETE.formatted(number));
        }
    }

    @Override
    public Collection<Unit> list(ListUnitsReq filter) {
        CriteriaBuilder criteriaBuilder = entityManagerProvider.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Unit> criteriaQuery = criteriaBuilder.createQuery(Unit.class);
        Root<Unit> root = criteriaQuery.from(Unit.class);

        Collection<Predicate> andPredicates = new ArrayList<>();
        // TODO: use field names like metadata
        andPredicates.add(criteriaBuilder.equal(root.get("isActive"), Boolean.TRUE));

        wrapField(filter::getNumberLike)
            .map(value -> "%" + value + "%")
            .map(value -> criteriaBuilder.like(root.get("number"), value))
            .ifPresent(andPredicates::add);

        wrapField(filter::getNameLike)
            .map(value -> "%" + value + "%")
            .map(value -> criteriaBuilder.like(root.get("name"), value))
            .ifPresent(andPredicates::add);

        wrapField(filter::getCreatedAtStart)
            .map(value -> criteriaBuilder.ge(root.get("createdAt"), value))
            .ifPresent(andPredicates::add);

        wrapField(filter::getCreatedAtEnd)
            .map(value -> criteriaBuilder.le(root.get("createdAt"), value))
            .ifPresent(andPredicates::add);

        wrapField(filter::getUpdatedAtStart)
            .map(value -> criteriaBuilder.ge(root.get("updatedAt"), value))
            .ifPresent(andPredicates::add);

        wrapField(filter::getUpdatedAtEnd)
            .map(value -> criteriaBuilder.le(root.get("updatedAt"), value))
            .ifPresent(andPredicates::add);

        if (!filter.getStatusIn().isEmpty()) {
            var values = filter.getStatusIn()
                .stream()
                .map(EnumUtils::getUnitStatusCodeByString)
                .toList();
            andPredicates.add(criteriaBuilder.in(root.get("status").in(values)));
        }

        if (!filter.getTypeIn().isEmpty()) {
            var values = filter.getTypeIn()
                .stream()
                .map(EnumUtils::getUnitTypeCodeByString)
                .toList();
            andPredicates.add(criteriaBuilder.in(root.get("type").in(values)));
        }

        var array = andPredicates.toArray(Predicate[]::new);
        Predicate wherePredicate = criteriaBuilder.and(array);

        criteriaQuery
            .select(root)
            .where(wherePredicate)
            .orderBy(getUnitOrderByCode(filter.getSortCode()).calcOrder(root, criteriaBuilder));

        return entityManagerProvider.getEntityManager().createQuery(criteriaQuery)
            .setFirstResult(filter.getPageNum() * filter.getItemsPerPage())
            .setMaxResults(filter.getItemsPerPage())
            .getResultList();
    }

    @Override
    public void patch() {

    }


    private <R> Optional<R> wrapField(Supplier<R> supplier) {
        return Optional.ofNullable(supplier.get());
    }


    private static UnitOrder getUnitOrderByCode(Integer code) {
        // TODO: custom error
        return Arrays.stream(UnitOrder.values())
            .filter(value -> value.getCode().equals(code))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Can not find ...."));
    }

}
