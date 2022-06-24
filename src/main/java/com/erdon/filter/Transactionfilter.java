package com.erdon.filter;

import com.erdon.utils.JdbcUtils;
import com.erdon.utils.MbUtils;

import javax.servlet.*;
import java.io.IOException;

public class Transactionfilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
        //    MbUtils.commit();
        } catch (Exception e) {
         //   MbUtils.rollback();
            e.printStackTrace();
        }finally {
         //   MbUtils.close();
        }
    }

    @Override
    public void destroy() {

    }
}
