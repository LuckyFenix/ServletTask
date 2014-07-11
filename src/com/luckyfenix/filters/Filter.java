package com.luckyfenix.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by LuckyFenix on 07.07.2014. ${TEMP}
 */
public class Filter implements javax.servlet.Filter
{
    public void destroy()
    {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
    {
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException
    {

    }

}
