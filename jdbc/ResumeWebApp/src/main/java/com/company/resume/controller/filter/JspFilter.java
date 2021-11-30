
package com.company.resume.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebFilter(filterName="JSPFileFilter",urlPatterns = {"*.jsp"})
public class JspFilter implements Filter{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletResponse res=(HttpServletResponse) response;
    res.sendRedirect("error?msg=not found");        
 }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        }

    @Override
    public void destroy() {
    }
}