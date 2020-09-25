package com.epam.mentoring.springmvc.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Kaikenov Adilhan
**/
@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity {

    @NotNull
    @Column(nullable = false, length = 50)
    private String username;

    @NotNull
    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 50)
    private String address;

    @Column
    private String photo;

    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Order> orders = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(final String photo) {
        this.photo = photo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(final Set<Order> orders) {
        this.orders = orders;
    }
}
