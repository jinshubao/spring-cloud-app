package com.jean.es.controller;

import com.jean.es.model.Log;
import com.jean.es.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jinshubao
 * @date 2017/7/17
 */
@RestController
@RequestMapping(value = "/log", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class EsController {
    Logger logger = LoggerFactory.getLogger(EsController.class);

    @Autowired
    LogService logService;

    @GetMapping("/list")
    ResponseEntity<List<Log>> list(String level) {
        List<Log> list = logService.findList(level);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
