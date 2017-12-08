package com.jean.user.api.service;

import com.jean.user.api.entity.Api;
import com.jean.user.api.model.response.ApiResponse;

import java.util.List;

public interface IApiService extends IBaseService<Api, Long> {

    List<ApiResponse> findAllNames(Long moduleId);
}
