package com.jean.auto.service;

import com.jean.auto.entity.Api;

import java.util.List;

public interface IApiService extends IBaseService<Api, Long> {

    List<Api> findByModuleId(Long moduleId);
}
