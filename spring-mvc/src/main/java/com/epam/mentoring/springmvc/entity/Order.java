package com.epam.mentoring.springmvc.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Kaikenov Adilhan
**/
@Entity
@Table(name = "orders")
public class Order extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orders_items",
            joinColumns = { @JoinColumn(name = "order_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "item_id", nullable = false, updatable = false) }
    )
    private Set<ProductItem> items = new HashSet<>();

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Set<ProductItem> getItems() {
        return items;
    }

    public void setItems(final Set<ProductItem> items) {
        this.items = items;
    }
}
