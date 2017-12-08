package com.jean.user.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProject extends EntityPathBase<Project> {

    private static final long serialVersionUID = 1371224468L;

    public static final QProject project = new QProject("project");

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

    public final ListPath<Module, QModule> modules = this.<Module, QModule>createList("modules", Module.class, QModule.class, PathInits.DIRECT2);

    //inherited
    public final StringPath name = _super.name;

    public QProject(String variable) {
        super(Project.class, forVariable(variable));
    }

    public QProject(Path<? extends Project> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProject(PathMetadata metadata) {
        super(Project.class, metadata);
    }

}

