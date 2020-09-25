package com.epam.mentoring.springmvc.security;

import com.epam.mentoring.springmvc.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Kaikenov Adilhan
**/
public class CommonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(CommonAuthenticationSuccessHandler.class);

    private static final String USERNAME_COOKIE_NAME = "username";
    private static final int SECONDS_PER_DAY = 24 * 60 * 60;

    private String urlForRedirection;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        addUsernameCookie(response, authentication); // Adding username cookie

        HttpSession session = request.getSession();
        User authUser = (User) authentication.getPrincipal();
        session.setAttribute("username", authUser.getUsername());
        session.setAttribute("authorities", authentication.getAuthorities());

        //set response status
        response.setStatus(HttpServletResponse.SC_OK);

        // since it is custom success handler, it's up to us to where to redirect the user after successful login
        response.sendRedirect(getUrlForRedirection());
    }

    public String getUrlForRedirection() {
        return urlForRedirection;
    }

    public void setUrlForRedirection(String urlForRedirection) {
        this.urlForRedirection = urlForRedirection;
    }

    private void addUsernameCookie(HttpServletResponse response, Authentication authentication) {
        final String username = getUsername(authentication);
        log.debug("CommonAuthenticationSuccessHandler: user {} has been logged in successfully.", username);

        CookieUtil.addCookie(response, USERNAME_COOKIE_NAME, username, SECONDS_PER_DAY);
        log.debug("Cookie {} with value {} has been added.", USERNAME_COOKIE_NAME, username);
    }

    private String getUsername(Authentication authentication) {
        final Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
