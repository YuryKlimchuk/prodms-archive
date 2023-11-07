package com.hydroyura.prodms.archive.data.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDBRate is a Querydsl query type for DBRate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDBRate extends EntityPathBase<DBRate> {

    private static final long serialVersionUID = -2100795164L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDBRate dBRate = new QDBRate("dBRate");

    public final QDBPart assembly;

    public final NumberPath<Long> count = createNumber("count", Long.class);

    public final QDBPart element;

    public final com.hydroyura.prodms.archive.data.entities.keys.QDBRateKey key;

    public QDBRate(String variable) {
        this(DBRate.class, forVariable(variable), INITS);
    }

    public QDBRate(Path<? extends DBRate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDBRate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDBRate(PathMetadata metadata, PathInits inits) {
        this(DBRate.class, metadata, inits);
    }

    public QDBRate(Class<? extends DBRate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.assembly = inits.isInitialized("assembly") ? new QDBPart(forProperty("assembly")) : null;
        this.element = inits.isInitialized("element") ? new QDBPart(forProperty("element")) : null;
        this.key = inits.isInitialized("key") ? new com.hydroyura.prodms.archive.data.entities.keys.QDBRateKey(forProperty("key")) : null;
    }

}

