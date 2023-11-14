package com.hydroyura.prodms.archive.data.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDBPartChange is a Querydsl query type for DBPartChange
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDBPartChange extends EntityPathBase<DBPartChange> {

    private static final long serialVersionUID = -884249977L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDBPartChange dBPartChange = new QDBPartChange("dBPartChange");

    public final com.hydroyura.prodms.archive.data.entities.keys.QDBPartChangeKey key;

    public final StringPath object = createString("object");

    public final EnumPath<com.hydroyura.prodms.archive.services.publisher.partchange.PartChangeEventType> operation = createEnum("operation", com.hydroyura.prodms.archive.services.publisher.partchange.PartChangeEventType.class);

    public final QDBPart part;

    public final DatePath<java.time.LocalDate> update = createDate("update", java.time.LocalDate.class);

    public final StringPath user = createString("user");

    public QDBPartChange(String variable) {
        this(DBPartChange.class, forVariable(variable), INITS);
    }

    public QDBPartChange(Path<? extends DBPartChange> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDBPartChange(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDBPartChange(PathMetadata metadata, PathInits inits) {
        this(DBPartChange.class, metadata, inits);
    }

    public QDBPartChange(Class<? extends DBPartChange> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.key = inits.isInitialized("key") ? new com.hydroyura.prodms.archive.data.entities.keys.QDBPartChangeKey(forProperty("key")) : null;
        this.part = inits.isInitialized("part") ? new QDBPart(forProperty("part")) : null;
    }

}

