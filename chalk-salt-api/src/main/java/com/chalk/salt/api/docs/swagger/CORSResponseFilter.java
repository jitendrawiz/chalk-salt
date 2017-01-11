package com.chalk.salt.api.docs.swagger;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CORSResponseFilter implements Filter
{

    @Override
    public void destroy()
        {
            // TODO Auto-generated method stub

        }

    @Override
    public void doFilter(ServletRequest webRequest, ServletResponse webResponse, FilterChain chain) throws IOException, ServletException
        {
            if (webResponse instanceof HttpServletResponse)
                {
                HttpServletResponse response = (HttpServletResponse) webResponse;
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
                response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
                response.addHeader("Access-Control-Max-Age", "1728000");

                }
            chain.doFilter(webRequest, webResponse);
        }

    @Override
    public void init(FilterConfig arg0) throws ServletException
        {
            // TODO Auto-generated method stub

        }

}
