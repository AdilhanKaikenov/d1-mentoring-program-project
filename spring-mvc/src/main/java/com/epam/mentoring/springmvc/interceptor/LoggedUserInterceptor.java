package com.epam.mentoring.springmvc.interceptor;

import com.epam.mentoring.springmvc.security.LoggedInUserHolder;
import com.epam.mentoring.springmvc.service.UserService;
import com.epam.mentoring.springmvc.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kaikenov Adilhan
**/
@Component
public class LoggedUserInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(LoggedUserInterceptor.class);

    @Autowired
    private LoggedInUserHolder loggedInUserHolder;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        this.storeCurrentUser(request, response);
        return super.preHandle(request, response, handler);
    }

    private void storeCurrentUser(final HttpServletRequest request, final HttpServletResponse response) {
        if (loggedInUserHolder.getUserVO() == null) {
            final String username = getUsername();
            try {
                final UserVO userVO = userService.findVoByUsername(username);
                loggedInUserHolder.setUserVO(userVO);
            } catch (Exception e) {
                log.error("Failed to store current user.", e);
            }
        }
    }

    private String getUsername() {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

}
