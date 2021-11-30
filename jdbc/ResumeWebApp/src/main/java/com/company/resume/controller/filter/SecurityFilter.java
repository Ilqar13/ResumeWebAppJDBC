
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



@WebFilter(filterName="SecurityFilter",urlPatterns = {"*"})
public class SecurityFilter implements Filter{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse res=(HttpServletResponse) response;
        if(req.getRequestURI().contains("login") || req.getRequestURI().contains("error")){
            chain.doFilter(req, res);
            return;
        }
        if(req.getSession().getAttribute("loggedInUser")!=null){
            chain.doFilter(req, res);
        }else{
            res.sendRedirect("login");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}