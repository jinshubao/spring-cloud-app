package com.jean.auto.service;

import com.jean.auto.entity.Parameter;

import java.util.List;

public interface IParameterService extends IBaseService<Parameter, Long> {

    List<Parameter> findByApiId(Long id);

    List<Parameter> findByTestCaseIdAndApiId(Long testCaseId, Long apiId);
}
