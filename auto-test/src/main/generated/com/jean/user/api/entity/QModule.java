package com.jean.user.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QModule is a Querydsl query type for Module
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QModule extends EntityPathBase<Module> {

    private static final long serialVersionUID = 370900113L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QModule module = new QModule("module");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<Api, QApi> apis = this.<Api, QApi>createList("apis", Api.class, QApi.class, PathInits.DIRECT2);

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

    public final QProject project;

    public QModule(String variable) {
        this(Module.class, forVariable(variable), INITS);
    }

    public QModule(Path<? extends Module> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QModule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QModule(PathMetadata metadata, PathInits inits) {
        this(Module.class, metadata, inits);
    }

    public QModule(Class<? extends Module> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project")) : null;
    }

}

