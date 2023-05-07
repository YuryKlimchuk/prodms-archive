package com.hydroyura.prodms.archive.data.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDBPartChangeKey is a Querydsl query type for DBPartChangeKey
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDBPartChangeKey extends BeanPath<DBPartChangeKey> {

    private static final long serialVersionUID = -1656563112L;

    public static final QDBPartChangeKey dBPartChangeKey = new QDBPartChangeKey("dBPartChangeKey");

    public final StringPath partNumber = createString("partNumber");

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QDBPartChangeKey(String variable) {
        super(DBPartChangeKey.class, forVariable(variable));
    }

    public QDBPartChangeKey(Path<? extends DBPartChangeKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDBPartChangeKey(PathMetadata metadata) {
        super(DBPartChangeKey.class, metadata);
    }

}

