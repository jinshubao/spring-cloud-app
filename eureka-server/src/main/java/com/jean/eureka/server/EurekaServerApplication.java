package com.jean.eureka.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.event.HeartbeatEvent;
import org.springframework.cloud.client.discovery.event.InstanceRegisteredEvent;
import org.springframework.cloud.client.discovery.event.ParentHeartbeatEvent;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(EurekaServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

    @EventListener
    public void onInstanceRegistered(InstanceRegisteredEvent<?> event) {
        logger.info("InstanceRegisteredEvent {}", event);
    }


    @EventListener
    public void onParentHeartbeat(ParentHeartbeatEvent event) {
        logger.info("ParentHeartbeatEvent {}", event);
    }

    @EventListener
    public void onApplicationEvent(HeartbeatEvent event) {
        logger.info("HeartbeatEvent {}", event);
    }
}
