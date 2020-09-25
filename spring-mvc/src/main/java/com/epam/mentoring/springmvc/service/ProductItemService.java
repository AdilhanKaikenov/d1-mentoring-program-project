package com.epam.mentoring.springmvc.service;

import com.epam.mentoring.springmvc.entity.ProductItem;
import com.epam.mentoring.springmvc.exception.PersistenceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Kaikenov Adilhan
**/
public interface ProductItemService {

    ProductItem createProductItem(ProductItem productItem) throws PersistenceException;

    Page<ProductItem> findAllPaginated(Pageable pageable) throws PersistenceException;

}
