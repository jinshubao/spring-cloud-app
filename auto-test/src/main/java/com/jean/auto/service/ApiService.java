package com.jean.auto.service;

import com.jean.auto.entity.Api;
import com.jean.auto.repository.ApiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiService extends BaseService<Api, Long> implements IApiService {
    @Override
    public List<Api> findByModuleId(Long moduleId) {
        return ((ApiRepository) repository).findByModuleId(moduleId);
    }
}
