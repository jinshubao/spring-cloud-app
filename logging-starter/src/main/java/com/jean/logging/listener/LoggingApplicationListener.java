package com.jean.logging.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * 监听应用启动事件，配置log
 * Created by jinshubao on 2017/7/12.
 */
public class LoggingApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            ApplicationEnvironmentPreparedEvent environmentPreparedEvent = (ApplicationEnvironmentPreparedEvent) event;
            ConfigurableEnvironment environment = environmentPreparedEvent.getEnvironment();

            Map<String, Object> systemProperties = environment.getSystemProperties();
            String remoteUlr = environment.getProperty("mylog.remoteUrl");
            if (!ObjectUtils.isEmpty(remoteUlr) && !systemProperties.containsKey(remoteUlr)) {
                systemProperties.put("LOG_REMOTE_URL", remoteUlr);
            }
        }
    }
}
