package com.epam.mentoring.web.filter;

import com.epam.mentoring.web.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kaikenov Adilhan
 **/
public class VisitCountCookieFilter implements Filter {

    private static final String VISIT_COUNT_COOKIE_NAME = "visit_count";
    private static final int SECONDS_PER_YEAR = 60 * 60 * 24 * 365;

    private static final String GET_METHOD = "GET";
    private static final String PATH_TO_IGNORE_PARAM_NAME = "pathToIgnore";

    private static String pathToIgnore;

    @Override
    public void init(FilterConfig config) throws ServletException {
        pathToIgnore = config.getInitParameter(PATH_TO_IGNORE_PARAM_NAME);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI();
        if (!path.contains(pathToIgnore) && request.getMethod().equalsIgnoreCase(GET_METHOD)) {
            increaseVisitCountCookieValue(request, response);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private void increaseVisitCountCookieValue(HttpServletRequest request, HttpServletResponse response) {
        String visitCountStr = CookieUtil.getCookieValue(request, VISIT_COUNT_COOKIE_NAME, "1");

        int visitCount = Integer.parseInt(visitCountStr);

        Cookie cookie = new Cookie(VISIT_COUNT_COOKIE_NAME, String.valueOf(visitCount + 1));
        cookie.setMaxAge(SECONDS_PER_YEAR);

        response.addCookie(cookie);
    }

}
