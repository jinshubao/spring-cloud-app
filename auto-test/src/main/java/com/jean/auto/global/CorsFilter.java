package com.jean.auto.global;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String refer = request.getHeader("Refer");
        String origin = request.getHeader("Origin");

        //TODO allow all
        if (refer != null) {
            response.setHeader("Access-Control-Allow-Origin", refer);

        } else if (origin != null) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        }
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "client, auth-token, x-auth-token, x-requested-with,Authorization,Origin, Accept, Content-Type,x-xsrf-token");
        if (!Objects.equals(request.getMethod(), "OPTIONS")) {
            chain.doFilter(req, res);
        }

    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}
}
