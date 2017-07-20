package com.jean.logging.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import com.jean.logging.listener.RedisListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zb on 2015/7/23.
 * redis配置
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisListener redisListener(CountDownLatch downLatch) {
        return new RedisListener(downLatch);
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory factory, RedisListener listener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.addMessageListener(listener, new ChannelTopic("logstash"));
        return container;
    }

    @Bean
    public CountDownLatch latch() {
        return new CountDownLatch(1);
    }


}
