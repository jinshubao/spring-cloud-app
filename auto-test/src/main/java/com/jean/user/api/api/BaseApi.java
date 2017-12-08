package com.jean.user.api.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

@Validated
public class BaseApi {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public Logger getLogger() {
        return logger;
    }
}
