package com.jean.auto.service;

import com.jean.auto.entity.Parameter;
import com.jean.auto.entity.Project;
import com.jean.auto.repository.ParameterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterService extends BaseService<Parameter, Long> implements IParameterService {

    @Override
    public List<Parameter> findByApiId(Long id) {
        return ((ParameterRepository)repository).findByApiId(id);
    }

    @Override
    public List<Parameter> findByTestCaseIdAndApiId(Long testCaseId, Long apiId) {
        return ((ParameterRepository)repository).findByTestCaseIdAndApiId(testCaseId,apiId);
    }
}
