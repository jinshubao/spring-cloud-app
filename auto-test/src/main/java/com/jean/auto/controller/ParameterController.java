package com.jean.auto.controller;

import com.jean.auto.constant.CommonConstant;
import com.jean.auto.entity.Parameter;
import com.jean.auto.model.common.ApiSimpleResultHelper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/parameters", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ParameterController extends BaseController<Parameter, Long> {

    @PutMapping
    ApiSimpleResultHelper<Parameter> modify(@RequestBody Parameter object) {
        object.setModifiedTime(new Date());
        object.setEnabled(true);
        Parameter o = baseService.save(object);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, o);
    }
}
