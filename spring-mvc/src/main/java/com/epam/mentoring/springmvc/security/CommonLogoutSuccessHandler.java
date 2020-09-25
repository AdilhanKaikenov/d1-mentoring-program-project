package com.epam.mentoring.springmvc.security;

import com.epam.mentoring.springmvc.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kaikenov Adilhan
**/
public class CommonLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(CommonLogoutSuccessHandler.class);

    private static final String USERNAME_COOKIE_NAME = "username";

    private String urlForRedirection;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        final String usernameCookieValue = CookieUtil.getCookieValue(request, USERNAME_COOKIE_NAME, null);
        if (usernameCookieValue != null) {
            CookieUtil.removeCookie(response, USERNAME_COOKIE_NAME);
            log.debug("CommonLogoutSuccessHandler: user {} has been logged out successfully.", usernameCookieValue);
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect(getUrlForRedirection());
    }

    public String getUrlForRedirection() {
        return this.urlForRedirection;
    }

    public void setUrlForRedirection(String urlForRedirection) {
        this.urlForRedirection = urlForRedirection;
    }
}
