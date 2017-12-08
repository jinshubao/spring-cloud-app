package com.jean.user.api.controller;

import com.jean.user.api.entity.Parameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/parameters", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ParameterController extends BaseController<Parameter, Long> {


}
