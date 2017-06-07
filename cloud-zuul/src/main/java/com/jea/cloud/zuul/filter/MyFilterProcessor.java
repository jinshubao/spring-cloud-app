package com.jea.cloud.zuul.filter;

import com.netflix.zuul.FilterProcessor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * <p>
 * 对于post阶段抛出异常的情况下，
 * 由error过滤器处理之后并不会在调用post阶段的请求，
 * 自然这些error.*参数也就不会被SendErrorFilter消费输出。
 * 所以，如果我们在自定义post过滤器的时候，没有正确的处理异常，
 * 就依然有可能出现日志中没有异常并且请求响应内容为空的问题
 * </p>
 * Created by jinshubao on 2017/6/6.
 */
public class MyFilterProcessor extends FilterProcessor {

    @Override
    public Object processZuulFilter(ZuulFilter filter) throws ZuulException {
        try {
            return super.processZuulFilter(filter);
        } catch (ZuulException e) {
            RequestContext ctx = RequestContext.getCurrentContext();
            ctx.set("failed.filter", filter);
            throw e;
        }
    }
}
