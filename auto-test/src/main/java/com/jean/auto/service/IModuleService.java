package com.jean.auto.service;

import com.jean.auto.entity.Module;
import com.jean.auto.model.response.ModuleResponse;

import java.util.List;

public interface IModuleService extends IBaseService<Module, Long> {

    List<ModuleResponse> findAllNames(Long projectId);
}
