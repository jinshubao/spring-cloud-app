package com.jean.auto.service;

import com.jean.auto.entity.Api;
import com.jean.auto.model.response.ApiResponse;

import java.util.List;

public interface IApiService extends IBaseService<Api, Long> {

    List<ApiResponse> findAllNames(Long moduleId);
}
