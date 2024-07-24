package com.hydroyura.prodms.archive.server.services.processors.filterchecker;

import com.hydroyura.prodms.archive.client.unit.FilterUnit;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UnitFilterChecker implements FilterChecker<FilterUnit> {

    private final Integer DEFAULT_PAGE = 0;
    private final Integer DEFAULT_PER_PAGE = 20;
    private final String DEFAULT_SORT = "number";
    private final String DEFAULT_SORT_ORDER = "ASC";

    @Override
    public FilterUnit check(@NotNull FilterUnit f) {
        checkField(f::getPage, f::setPage, DEFAULT_PAGE);
        checkField(f::getPerPage, f::setPerPage, DEFAULT_PER_PAGE);
        checkField(f::getSort, f::setSort, DEFAULT_SORT);
        checkField(f::getSortOrder, f::setSortOrder, DEFAULT_SORT_ORDER);
        return f;
    }
}
