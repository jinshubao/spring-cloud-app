package com.jean.logging.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jinshubao
 * @date 2017/7/11
 */
@ConfigurationProperties(prefix = "logging")
public class LogProperties {

    private String expression;

    private String location;

    private Boolean logParams;

    private Boolean logResult;

    private Boolean logCost;

    private String url;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getLogParams() {
        return logParams;
    }

    public void setLogParams(Boolean logParams) {
        this.logParams = logParams;
    }

    public Boolean getLogResult() {
        return logResult;
    }

    public void setLogResult(Boolean logResult) {
        this.logResult = logResult;
    }

    public Boolean getLogCost() {
        return logCost;
    }

    public void setLogCost(Boolean logCost) {
        this.logCost = logCost;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
