package com.epam.mentoring.springmvc.controller;

import com.epam.mentoring.springmvc.entity.ProductItem;
import com.epam.mentoring.springmvc.entity.User;
import com.epam.mentoring.springmvc.model.ProductItemModel;
import com.epam.mentoring.springmvc.model.UserModel;
import com.epam.mentoring.springmvc.security.LoggedInUserHolder;
import com.epam.mentoring.springmvc.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author Kaikenov Adilhan
**/
public abstract class AbstractController {

    private static final Logger log = LoggerFactory.getLogger(AbstractController.class);

    protected static final String NOTIFICATION = "notification";

    protected static final String HOME_VIEW_NAME = "home";
    protected static final String HOME_URL = "/home";
    protected static final String REDIRECT_HOME_URL = "redirect:" + HOME_URL;

    @Autowired
    private LoggedInUserHolder loggedInUserHolder;

    @ModelAttribute("currentUser")
    public UserVO addCurrentUser() {
        return this.getCurrentUser();
    }

    private UserVO getCurrentUser() {
        return loggedInUserHolder.getUserVO();
    }

    protected User mapUserModelToEntity(final UserModel userModel) {
        final User user = new User();
        user.setUsername(userModel.getUsername());
        user.setPassword(userModel.getPassword());
        user.setAddress(userModel.getAddress());
        user.setPhoto(userModel.getPhoto());
        user.setRole(userModel.getRole());
        return user;
    }

    protected UserModel mapUserVoToModel(final UserVO userVo) {
        final UserModel user = new UserModel();
        user.setUsername(userVo.getUsername());
        user.setPassword(userVo.getPassword());
        user.setAddress(userVo.getAddress());
        user.setPhoto(userVo.getPhoto());
        user.setRole(userVo.getRole());
        return user;
    }

    protected ProductItem mapProductItemModelToEntity(final ProductItemModel productItemModel) {
        final ProductItem productItem = new ProductItem();
        productItem.setProductName(productItemModel.getProductName());
        productItem.setPrice(productItemModel.getPrice());
        return productItem;
    }
}
