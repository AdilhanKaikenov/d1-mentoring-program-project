package com.epam.mentoring.springmvc.service.impl;

import com.epam.mentoring.springmvc.entity.ProductItem;
import com.epam.mentoring.springmvc.exception.PersistenceException;
import com.epam.mentoring.springmvc.repository.ProductItemRepository;
import com.epam.mentoring.springmvc.service.ProductItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author Kaikenov Adilhan
**/
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = PersistenceException.class)
public class ProductItemServiceImpl implements ProductItemService {

    private static final Logger log = LoggerFactory.getLogger(ProductItemServiceImpl.class);

    @Autowired
    private ProductItemRepository productItemRepository;

    @Override
    public ProductItem createProductItem(ProductItem productItem) throws PersistenceException {
        Assert.notNull(productItem, "ProductItem must not be null!");

        try {
            return productItemRepository.save(productItem);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new PersistenceException("Failed to create a new product. ", e);
        }
    }

    @Override
    public Page<ProductItem> findAllPaginated(Pageable pageable) throws PersistenceException {

        try {
            return productItemRepository.findAll(pageable);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new PersistenceException("Failed to fetch products. ", e);
        }
    }
}
