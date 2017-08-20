package com.jean.test.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.event.HeartbeatEvent;
import org.springframework.cloud.client.discovery.event.InstanceRegisteredEvent;
import org.springframework.cloud.client.discovery.event.ParentHeartbeatEvent;
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
        logger.info("============ApplicationReadyEvent {}", event.getSource());
    }

    @EventListener
    public void onInstanceRegistered(InstanceRegisteredEvent<?> event) {
        logger.info("============InstanceRegisteredEvent {}", event);
    }

    @EventListener
    public void onParentHeartbeat(ParentHeartbeatEvent event) {
        logger.info("============ParentHeartbeatEvent {}", event);
    }

    @EventListener
    public void onApplicationEvent(HeartbeatEvent event) {
        logger.info("============HeartbeatEvent {}", event);
    }
}
