package com.epam.mentoring.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kaikenov Adilhan
**/
@Controller
public class AuthenticationController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    private static final String LOGIN_URL = "/login";

    private static final String ACCESS_DENIED_URL = "/access-denied";
    private static final String ACCESS_DENIED_PAGE_VIEW_NAME = "access-denied-page";

    @GetMapping(LOGIN_URL)
    public ModelAndView processLogin(
            @RequestParam(value = "error",required = false) String error,
            @RequestParam(value = "logout",	required = false) String logout) {

        final ModelAndView modelAndView = new ModelAndView();
        if (error != null) {
            modelAndView.addObject("error", "invalid.authentication.form.message");
        }

        if (logout != null) {
            modelAndView.addObject("message", "logout.info.message");
        }

        modelAndView.setViewName(HOME_VIEW_NAME);
        return modelAndView;
    }

    @GetMapping(ACCESS_DENIED_URL)
    public ModelAndView accessDeniedPage() {
        return new ModelAndView(ACCESS_DENIED_PAGE_VIEW_NAME);
    }

}
