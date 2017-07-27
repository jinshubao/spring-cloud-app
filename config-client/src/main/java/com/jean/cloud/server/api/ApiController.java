package com.jean.cloud.server.api;

import com.jean.cloud.server.config.MyConfigBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jinshubao on 2017/6/6.
 */
@RefreshScope
@RestController
@RequestMapping(value = "/config", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    private final MyConfigBean configBean;

    @Autowired
    public ApiController(MyConfigBean configBean) {
        this.configBean = configBean;
    }


    @GetMapping("/value")
    public String title() {
        logger.info("-----------{}", configBean);
        return configBean.getTitle() + ", " + configBean.getName();
    }
}
