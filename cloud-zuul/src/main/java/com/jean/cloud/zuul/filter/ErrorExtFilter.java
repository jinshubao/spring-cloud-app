package com.jean.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * Created by jinshubao on 2017/6/6.
 */
@Component
public class ErrorExtFilter extends SendErrorFilter {

    static final Logger logger = LoggerFactory.getLogger(ErrorExtFilter.class);

    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 仅处理来自post过滤器引起的异常
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ZuulFilter failedFilter = (ZuulFilter) ctx.get("failed.filter");
        return failedFilter != null && FilterConstants.POST_TYPE.equals(failedFilter.filterType());
    }


}
