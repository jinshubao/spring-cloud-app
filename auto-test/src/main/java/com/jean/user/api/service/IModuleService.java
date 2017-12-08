package com.jean.user.api.service;

import com.jean.user.api.entity.Module;
import com.jean.user.api.model.response.ModuleResponse;

import java.util.List;

public interface IModuleService extends IBaseService<Module, Long> {

    List<ModuleResponse> findAllNames(Long projectId);
}
