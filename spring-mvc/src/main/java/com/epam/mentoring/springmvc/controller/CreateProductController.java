package com.epam.mentoring.springmvc.controller;

import com.epam.mentoring.springmvc.entity.ProductItem;
import com.epam.mentoring.springmvc.model.ProductItemModel;
import com.epam.mentoring.springmvc.service.ProductItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author Kaikenov Adilhan
**/
@Controller
public class CreateProductController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(CreateProductController.class);

    private static final String PAGE_MODEL = "productItemModel";
    private static final String CREATE_PRODUCT_VIEW_NAME = "create-product";
    private static final String PRODUCT_CREATE_URL = "/product/create";

    @Autowired
    private ProductItemService productItemService;

    @GetMapping(PRODUCT_CREATE_URL)
    public ModelAndView renderPage() {
        return new ModelAndView(CREATE_PRODUCT_VIEW_NAME, PAGE_MODEL, new ProductItemModel());
    }

    @PostMapping(PRODUCT_CREATE_URL)
    public ModelAndView createProduct(@Valid @ModelAttribute ProductItemModel productItemModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.debug("Create Product form is invalid.");
            return new ModelAndView(CREATE_PRODUCT_VIEW_NAME);
        }

        try {
            ProductItem productItem = mapProductItemModelToEntity(productItemModel);
            productItemService.createProductItem(productItem);
        } catch (Exception e) {
            log.error("Failed to create a new product.", e);
            return new ModelAndView(CREATE_PRODUCT_VIEW_NAME, NOTIFICATION, "create.product.failed.notification.message");
        }

        redirectAttributes.addFlashAttribute(NOTIFICATION, "create.product.success.notification.message");
        return new ModelAndView(REDIRECT_HOME_URL);
    }

}
