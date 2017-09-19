package com.jean.auto.service;

import com.jean.auto.entity.Module;
import com.jean.auto.repository.ModuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService extends BaseService<Module, Long> implements IModuleService {

    @Override
    public List<Module> findByProjectId(Long id) {
        return ((ModuleRepository) repository).findByProjectId(id);
    }
}
