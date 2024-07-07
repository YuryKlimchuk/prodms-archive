package com.hydroyura.prodms.archive.server.services.processors.filterchecker;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface FilterChecker<Filter> {

    Filter check(Filter f);

    default  <T> void checkField(Supplier<T> s, Consumer<T> c, T defVal) {
        Optional
                .ofNullable(s.get())
                .ifPresentOrElse(a -> {}, () -> c.accept(defVal));
    }

}
