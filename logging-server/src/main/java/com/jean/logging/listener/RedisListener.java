package com.jean.logging.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jinshubao on 2017/7/14.
 */
public class RedisListener implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(RedisListener.class);

    private CountDownLatch countDownLatch;

    public RedisListener(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        logger.info("Channel:{}-Body:{}-pattern:{}", new String(message.getChannel()), new String(message.getBody()), new String(pattern));
        countDownLatch.countDown();
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }
}
