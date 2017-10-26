package com.jean.auto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTestUnit is a Querydsl query type for TestUnit
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTestUnit extends EntityPathBase<TestUnit> {

    private static final long serialVersionUID = -589768165L;

    public static final QTestUnit testUnit = new QTestUnit("testUnit");

    public final QBaseEntity _super = new QBaseEntity(this);

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

    public QTestUnit(String variable) {
        super(TestUnit.class, forVariable(variable));
    }

    public QTestUnit(Path<? extends TestUnit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTestUnit(PathMetadata metadata) {
        super(TestUnit.class, metadata);
    }

}

