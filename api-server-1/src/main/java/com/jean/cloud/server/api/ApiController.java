package com.jean.cloud.server.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jinshubao on 2017/6/6.
 */
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}/detail")
    public String userDetail(@PathVariable("userId") Integer userId) {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://api-server-2/user/{userId}/detail", String.class, userId);
        String body = entity.getBody();
        logger.info("body {}", body);
        return body;
    }
}
