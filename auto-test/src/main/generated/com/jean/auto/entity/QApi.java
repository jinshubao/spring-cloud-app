package com.jean.auto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApi is a Querydsl query type for Api
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QApi extends EntityPathBase<Api> {

    private static final long serialVersionUID = -54206955L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApi api = new QApi("api");

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

    public final QModule module;

    //inherited
    public final StringPath name = _super.name;

    public final NumberPath<Integer> port = createNumber("port", Integer.class);

    public final StringPath protocol = createString("protocol");

    public final StringPath url = createString("url");

    public QApi(String variable) {
        this(Api.class, forVariable(variable), INITS);
    }

    public QApi(Path<? extends Api> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApi(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApi(PathMetadata metadata, PathInits inits) {
        this(Api.class, metadata, inits);
    }

    public QApi(Class<? extends Api> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.module = inits.isInitialized("module") ? new QModule(forProperty("module"), inits.get("module")) : null;
    }

}

