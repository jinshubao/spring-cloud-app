package com.jean.auto.service;

import com.jean.auto.entity.Module;
import com.jean.auto.model.response.ModuleResponse;
import com.jean.auto.repository.ModuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuleService extends BaseService<Module, Long> implements IModuleService {

    @Override
    public List<ModuleResponse> findAllNames(Long projectId) {
        return ((ModuleRepository) repository).findByProjectId(projectId).stream().map((module) ->
                new ModuleResponse(module.getId(), module.getName(), module.getDescription())).collect(Collectors.toList());
    }
}
