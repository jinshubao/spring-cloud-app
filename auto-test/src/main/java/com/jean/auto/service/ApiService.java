package com.jean.auto.service;

import com.jean.auto.entity.Api;
import com.jean.auto.entity.QApi;
import com.jean.auto.model.response.ApiResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiService extends BaseService<Api, Long> implements IApiService {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ApiResponse> findAllNames(Long moduleId) {
        QApi _q_api = QApi.api;
        JPAQuery<ApiResponse> jpaQuery = jpaQueryFactory
                .select(Projections.constructor(ApiResponse.class, _q_api.id, _q_api.name, _q_api.description))
                .from(_q_api);
        if (moduleId != null) {
            jpaQuery = jpaQuery.where(_q_api.module.id.eq(moduleId));
        }
        return jpaQuery.fetch();

    }
}
