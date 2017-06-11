package com.jean.cloud.server.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jinshubao on 2017/6/6.
 */
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

    @GetMapping("/{userId}/detail")
    public Map<String, Object> userDetail(@PathVariable("userId") Integer userId) {

        Map<String, Object> detail = new HashMap<>();
        detail.put("userId", userId);
        detail.put("name", "zhangsan");
        detail.put("age", "25");
        detail.put("gender", "man");
        detail.put("address", "宜山路");
        return detail;
    }
}
