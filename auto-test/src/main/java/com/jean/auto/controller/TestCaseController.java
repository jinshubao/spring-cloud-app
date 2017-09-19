package com.jean.auto.controller;

import com.jean.auto.constant.CommonConstant;
import com.jean.auto.entity.TestCase;
import com.jean.auto.model.common.ApiSimpleResultHelper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/cases", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestCaseController extends BaseController<TestCase, Long> {

    @PutMapping
    ApiSimpleResultHelper<TestCase> modify(@RequestBody TestCase object) {
        object.setModifiedTime(new Date());
        object.setEnabled(true);
        TestCase o = baseService.save(object);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, o);
    }

}
