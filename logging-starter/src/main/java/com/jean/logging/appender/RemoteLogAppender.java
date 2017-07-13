package com.jean.logging.appender;

import ch.qos.logback.core.AppenderBase;
import org.slf4j.event.LoggingEvent;

/**
 * Created by jinshubao on 2017/7/11.
 */
public class RemoteLogAppender extends AppenderBase<LoggingEvent> {

    String url;

    @Override
    protected void append(LoggingEvent event) {
        //TODO 发送日志到远程处理

    }

    public void setUrl(String url) {
        this.url = url;
    }
}
