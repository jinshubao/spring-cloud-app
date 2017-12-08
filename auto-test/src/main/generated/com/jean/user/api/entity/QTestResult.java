package com.jean.user.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTestResult is a Querydsl query type for TestResult
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTestResult extends EntityPathBase<TestResult> {

    private static final long serialVersionUID = 74579700L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTestResult testResult = new QTestResult("testResult");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath assertResult = createBoolean("assertResult");

    //inherited
    public final DateTimePath<java.util.Date> createdTime = _super.createdTime;

    //inherited
    public final StringPath description = _super.description;

    //inherited
    public final BooleanPath enabled = _super.enabled;

    public final StringPath exception = createString("exception");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> modifiedTime = _super.modifiedTime;

    //inherited
    public final StringPath name = _super.name;

    public final StringPath parameter = createString("parameter");

    public final StringPath responseBody = createString("responseBody");

    public final StringPath responseHeader = createString("responseHeader");

    public final NumberPath<Integer> statusCode = createNumber("statusCode", Integer.class);

    public final QTestCase testCase;

    public final StringPath url = createString("url");

    public QTestResult(String variable) {
        this(TestResult.class, forVariable(variable), INITS);
    }

    public QTestResult(Path<? extends TestResult> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTestResult(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTestResult(PathMetadata metadata, PathInits inits) {
        this(TestResult.class, metadata, inits);
    }

    public QTestResult(Class<? extends TestResult> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.testCase = inits.isInitialized("testCase") ? new QTestCase(forProperty("testCase")) : null;
    }

}

