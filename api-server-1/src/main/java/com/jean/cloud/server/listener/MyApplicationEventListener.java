package com.jean.cloud.server.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by jinshubao on 2017/6/11.
 */
@Component
public class MyApplicationEventListener {
    private static final Logger logger = LoggerFactory.getLogger(MyApplicationEventListener.class);

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {

        logger.info("ApplicationReadyEvent {}", event.getSource());
    }
}
