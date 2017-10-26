package com.jean.auto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTestAssert is a Querydsl query type for TestAssert
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTestAssert extends EntityPathBase<TestAssert> {

    private static final long serialVersionUID = -399201763L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTestAssert testAssert = new QTestAssert("testAssert");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath assertKey = createString("assertKey");

    public final StringPath assertType = createString("assertType");

    public final StringPath assertValue = createString("assertValue");

    //inherited
    public final DateTimePath<java.util.Date> createdTime = _super.createdTime;

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

    public final QTestCase testCase;

    public QTestAssert(String variable) {
        this(TestAssert.class, forVariable(variable), INITS);
    }

    public QTestAssert(Path<? extends TestAssert> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTestAssert(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTestAssert(PathMetadata metadata, PathInits inits) {
        this(TestAssert.class, metadata, inits);
    }

    public QTestAssert(Class<? extends TestAssert> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.testCase = inits.isInitialized("testCase") ? new QTestCase(forProperty("testCase")) : null;
    }

}

