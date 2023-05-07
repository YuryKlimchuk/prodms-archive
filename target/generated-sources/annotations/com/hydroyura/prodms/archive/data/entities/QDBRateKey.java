package com.hydroyura.prodms.archive.data.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDBRateKey is a Querydsl query type for DBRateKey
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDBRateKey extends BeanPath<DBRateKey> {

    private static final long serialVersionUID = 1474781915L;

    public static final QDBRateKey dBRateKey = new QDBRateKey("dBRateKey");

    public final StringPath assemblyId = createString("assemblyId");

    public final StringPath elementId = createString("elementId");

    public final NumberPath<Long> replacement = createNumber("replacement", Long.class);

    public QDBRateKey(String variable) {
        super(DBRateKey.class, forVariable(variable));
    }

    public QDBRateKey(Path<? extends DBRateKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDBRateKey(PathMetadata metadata) {
        super(DBRateKey.class, metadata);
    }

}

