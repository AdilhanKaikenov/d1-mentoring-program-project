package com.epam.mentoring.springmvc.repository;

import com.epam.mentoring.springmvc.entity.ProductItem;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Kaikenov Adilhan
**/
public interface ProductItemRepository extends PagingAndSortingRepository<ProductItem, Long> {

}
