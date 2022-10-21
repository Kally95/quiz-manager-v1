package com.example.quizmanagerv1;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

         if(!req.getRequestURI() .equals("/login") && !req.getRequestURI().contains(".") && req.getSession().getAttribute("user") == null) {
             resp.sendRedirect("/login");
             return;
         }

        chain.doFilter(request, response);
    }
}

