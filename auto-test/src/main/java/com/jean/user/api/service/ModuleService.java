package com.jean.user.api.service;

import com.jean.user.api.entity.Module;
import com.jean.user.api.entity.QModule;
import com.jean.user.api.model.response.ModuleResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService extends BaseService<Module, Long> implements IModuleService {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ModuleResponse> findAllNames(Long projectId) {

        QModule qModule = QModule.module;
        JPAQuery<ModuleResponse> jpaQuery = jpaQueryFactory
                .select(Projections.constructor(ModuleResponse.class, qModule.id, qModule.name, qModule.description))
                .from(qModule);
        if (projectId != null) {
            jpaQuery.where(qModule.project.id.eq(projectId));
        }
        return jpaQuery.fetch();
    }
}
