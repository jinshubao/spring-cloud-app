package com.jean.auto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QParameter is a Querydsl query type for Parameter
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QParameter extends EntityPathBase<Parameter> {

    private static final long serialVersionUID = 2041048932L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QParameter parameter = new QParameter("parameter");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> createdTime = _super.createdTime;

    public final StringPath defaultValue = createString("defaultValue");

    //inherited
    public final StringPath description = _super.description;

    //inherited
    public final BooleanPath enabled = _super.enabled;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> modifiedTime = _super.modifiedTime;

    //inherited
    public final StringPath name = _super.name;

    public final StringPath parameterKey = createString("parameterKey");

    public final StringPath parameterType = createString("parameterType");

    public final StringPath parameterValue = createString("parameterValue");

    public final BooleanPath required = createBoolean("required");

    public final QTestCase testCase;

    public QParameter(String variable) {
        this(Parameter.class, forVariable(variable), INITS);
    }

    public QParameter(Path<? extends Parameter> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QParameter(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QParameter(PathMetadata metadata, PathInits inits) {
        this(Parameter.class, metadata, inits);
    }

    public QParameter(Class<? extends Parameter> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.testCase = inits.isInitialized("testCase") ? new QTestCase(forProperty("testCase")) : null;
    }

}

