package com.epam.mentoring.springmvc.controller;

import com.epam.mentoring.springmvc.entity.ProductItem;
import com.epam.mentoring.springmvc.model.PaginationModel;
import com.epam.mentoring.springmvc.service.ProductItemService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kaikenov Adilhan
**/
@Controller
public class HomePageController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(HomePageController.class);

    private static final String PAGE_PARAM_NAME = "page";
    private static final Integer HOME_PAGE_SIZE = 2;
    private static final String PAGINATION_MODEL = "paginationModel";
    private static final String PRODUCT_ITEMS = "productItems";

    @Autowired
    private ProductItemService productItemService;

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView(REDIRECT_HOME_URL);
    }

    @GetMapping(HOME_URL)
    public ModelAndView renderHome(@RequestParam(value = PAGE_PARAM_NAME, required = false) String page) {
        final ModelAndView modelAndView = new ModelAndView(HOME_VIEW_NAME);
        try {
            final int pageNum = getPageNum(page);
            final PageRequest pageRequest = PageRequest.of(pageNum, HOME_PAGE_SIZE);
            final Page<ProductItem> productItemPage = productItemService.findAllPaginated(pageRequest);

            final long totalElements = productItemPage.getTotalElements();
            final int size = productItemPage.getSize();

            final PaginationModel paginationModel = new PaginationModel(totalElements, HOME_PAGE_SIZE, size);

            modelAndView.addObject(PAGINATION_MODEL, paginationModel);
            modelAndView.addObject(PRODUCT_ITEMS, productItemPage.getContent());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return modelAndView;
    }

    private int getPageNum(String page) {
        int pageNum = 0;
        if (StringUtils.isNotBlank(page)) {
            pageNum = Integer.parseInt(page) - 1;
        }
        return pageNum;
    }

}
