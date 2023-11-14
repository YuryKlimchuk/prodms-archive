package com.hydroyura.prodms.archive.data.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDBPart is a Querydsl query type for DBPart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDBPart extends EntityPathBase<DBPart> {

    private static final long serialVersionUID = -2100854793L;

    public static final QDBPart dBPart = new QDBPart("dBPart");

    public final DatePath<java.time.LocalDate> created = createDate("created", java.time.LocalDate.class);

    public final StringPath info = createString("info");

    public final StringPath name = createString("name");

    public final StringPath number = createString("number");

    public final StringPath otherFile = createString("otherFile");

    public final StringPath pdf = createString("pdf");

    public final EnumPath<com.hydroyura.prodms.archive.data.entities.enums.DBPartStatus> status = createEnum("status", com.hydroyura.prodms.archive.data.entities.enums.DBPartStatus.class);

    public final EnumPath<com.hydroyura.prodms.archive.data.entities.enums.DBPartType> type = createEnum("type", com.hydroyura.prodms.archive.data.entities.enums.DBPartType.class);

    public final DatePath<java.time.LocalDate> updated = createDate("updated", java.time.LocalDate.class);

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QDBPart(String variable) {
        super(DBPart.class, forVariable(variable));
    }

    public QDBPart(Path<? extends DBPart> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDBPart(PathMetadata metadata) {
        super(DBPart.class, metadata);
    }

}

