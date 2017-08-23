package com.terabits.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CORSFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CORSFilter.class);

    private List<String> corsOriginList;

    //@Override
    public void destroy() {

    }

    //@Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String curOrigin = request.getHeader("Origin");
        LOGGER.info("当前访问来源是：{}", curOrigin);
        // 从列表中获取，可以将数据放入缓存
        if (corsOriginList.contains(curOrigin)) {
            response.setHeader("Access-Control-Allow-Origin", curOrigin);
        } else {
            return ;
        }

        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        chain.doFilter(req, res);
    }

    //@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        corsOriginList = new ArrayList<String>();
        // 初始化可访问的域名列表
        corsOriginList.add("http://192.168.31.134:3000");
        //corsOriginList.add("http://localhost:8080");
    }

}