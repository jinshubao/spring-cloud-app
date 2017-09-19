package com.jean.auto.service;

import com.jean.auto.entity.Module;

import java.util.List;

public interface IModuleService extends IBaseService<Module, Long> {

    List<Module> findByProjectId(Long id);
}
