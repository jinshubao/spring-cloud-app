package com.jean.user.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTestCase is a Querydsl query type for TestCase
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTestCase extends EntityPathBase<TestCase> {

    private static final long serialVersionUID = -590316601L;

    public static final QTestCase testCase = new QTestCase("testCase");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> createdTime = _super.createdTime;

    //inherited
    public final StringPath description = _super.description;

    //inherited
    public final BooleanPath enabled = _super.enabled;

    public final StringPath host = createString("host");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath method = createString("method");

    //inherited
    public final DateTimePath<java.util.Date> modifiedTime = _super.modifiedTime;

    //inherited
    public final StringPath name = _super.name;

    public final ListPath<Parameter, QParameter> parameters = this.<Parameter, QParameter>createList("parameters", Parameter.class, QParameter.class, PathInits.DIRECT2);

    public final NumberPath<Integer> port = createNumber("port", Integer.class);

    public final StringPath protocol = createString("protocol");

    public final ListPath<TestAssert, QTestAssert> testAsserts = this.<TestAssert, QTestAssert>createList("testAsserts", TestAssert.class, QTestAssert.class, PathInits.DIRECT2);

    public final ListPath<TestResult, QTestResult> testResults = this.<TestResult, QTestResult>createList("testResults", TestResult.class, QTestResult.class, PathInits.DIRECT2);

    public final StringPath url = createString("url");

    public QTestCase(String variable) {
        super(TestCase.class, forVariable(variable));
    }

    public QTestCase(Path<? extends TestCase> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTestCase(PathMetadata metadata) {
        super(TestCase.class, metadata);
    }

}

