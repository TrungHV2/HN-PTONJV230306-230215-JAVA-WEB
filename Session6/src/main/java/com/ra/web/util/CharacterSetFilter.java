package com.ra.web.util;

import javax.servlet.*;
import java.io.IOException;

public class CharacterSetFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }
}
