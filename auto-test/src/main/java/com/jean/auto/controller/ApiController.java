package com.jean.auto.controller;

import com.jean.auto.constant.CommonConstant;
import com.jean.auto.entity.Api;
import com.jean.auto.entity.Parameter;
import com.jean.auto.model.common.ApiSimpleResultHelper;
import com.jean.auto.service.IParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/apis", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiController extends BaseController<Api, Long> {
    @Autowired
    private IParameterService parameterService;

    @GetMapping("/parameters")
    ApiSimpleResultHelper<List<Parameter>> parameters(@RequestParam(value = "api_id") Long apiId) {
        List<Parameter> parameters = parameterService.findByApiId(apiId);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, parameters);
    }


    @PutMapping
    ApiSimpleResultHelper<Api> modify(@RequestBody Api object) {
        object.setModifiedTime(new Date());
        object.setEnabled(true);
        Api o = baseService.save(object);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, o);
    }

}
