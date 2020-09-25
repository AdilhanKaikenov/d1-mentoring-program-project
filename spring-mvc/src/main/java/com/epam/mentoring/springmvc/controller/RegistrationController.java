package com.epam.mentoring.springmvc.controller;

import com.epam.mentoring.springmvc.entity.Role;
import com.epam.mentoring.springmvc.entity.User;
import com.epam.mentoring.springmvc.exception.UsernameAlreadyExistException;
import com.epam.mentoring.springmvc.model.UserModel;
import com.epam.mentoring.springmvc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author Kaikenov Adilhan
**/
@Controller
public class RegistrationController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    private static final String SIGN_UP_URL = "/sign-up";

    private static final String REGISTRATION_VIEW_NAME = "registration";
    private static final String PAGE_MODEL = "userModel";

    @Autowired
    private UserService userService;

    @GetMapping(SIGN_UP_URL)
    public ModelAndView renderPage() {
        final ModelAndView modelAndView = new ModelAndView(REGISTRATION_VIEW_NAME);
        modelAndView.addObject("roles", Role.values());
        modelAndView.addObject(PAGE_MODEL, new UserModel());
        return modelAndView;
    }

    @PostMapping(SIGN_UP_URL)
    public ModelAndView signUp(@Valid @ModelAttribute(PAGE_MODEL) UserModel userModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.debug("Registration form is invalid.");
            return new ModelAndView(REGISTRATION_VIEW_NAME);
        }

        final User user = mapUserModelToEntity(userModel);

        try {
            userService.signUp(user);
        } catch (UsernameAlreadyExistException e) {
            log.error("Username already exist", e);
            return new ModelAndView(REGISTRATION_VIEW_NAME, NOTIFICATION, "username.exist.notification.message");
        } catch (Exception e) {
            log.error("Failed to register a new user.", e);
            return new ModelAndView(REGISTRATION_VIEW_NAME, NOTIFICATION, "registration.failed.notification.message");
        }

        return new ModelAndView(HOME_VIEW_NAME, NOTIFICATION, "registration.success.notification.message");
    }

}
