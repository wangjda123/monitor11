package com.example.monitor.configuration.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class AuthFilter extends BaseFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //System.out.println("auth filter init 被调用");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        //System.out.println("auth filter doFilter 被调用");
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        //System.out.println("auth filter destroy 被调用");
        cache.shutDown();
    }
}
