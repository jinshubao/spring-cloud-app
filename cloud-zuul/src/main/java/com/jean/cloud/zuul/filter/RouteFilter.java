package com.jean.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * Created by jinshubao on 2017/6/6.
 */
@Component
public class RouteFilter extends ZuulFilter {
    static final Logger logger = LoggerFactory.getLogger(RouteFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        logger.info("{} filter", filterType());
        return null;
    }
}
