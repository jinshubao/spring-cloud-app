package com.jean.cloud.server.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
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
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    Tracer tracer;

    @GetMapping("/{userId}/detail")
    @HystrixCommand(fallbackMethod = "error")
    public Map userDetail(@PathVariable("userId") Integer userId) {
        ResponseEntity<Map> entity = restTemplate.getForEntity("http://api-server-2/user/{userId}/detail", Map.class, userId);
        Map body = entity.getBody();
        logger.info("body {}", body);
        Span currentSpan = tracer.getCurrentSpan();
        if (currentSpan != null) {
            currentSpan.tag("参数", userId.toString());
            currentSpan.tag("响应", body.toString());
        }
        return body;
    }

    public Map error(Integer userId) {
        Map result = new HashMap();
        result.put("code", "999");
        result.put("userId", userId);
        result.put("msg", "这是错误信息");
        return result;
    }

}
