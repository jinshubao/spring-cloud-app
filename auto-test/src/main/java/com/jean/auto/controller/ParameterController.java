package com.jean.auto.controller;

import com.jean.auto.entity.Parameter;
import com.jean.auto.service.ITestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/parameters", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ParameterController extends BaseController<Parameter, Long> {

    @Autowired
    private ITestCaseService testCaseService;

}
