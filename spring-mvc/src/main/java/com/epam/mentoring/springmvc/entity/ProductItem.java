package com.epam.mentoring.springmvc.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Kaikenov Adilhan
**/
@Entity
@Table(name = "products")
public class ProductItem extends AbstractBaseEntity {

    @NotNull
    @Column(name = "item_name", nullable = false)
    private String productName;

    @NotNull
    @Column(name = "item_price", nullable = false)
    private BigDecimal price;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "items")
    private Set<Order> orders = new HashSet<>();

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(final Set<Order> orders) {
        this.orders = orders;
    }
}
