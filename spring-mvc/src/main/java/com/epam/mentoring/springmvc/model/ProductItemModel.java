package com.epam.mentoring.springmvc.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Kaikenov Adilhan
**/
public class ProductItemModel implements Serializable {

    @NotBlank(message = "{form.error.required}")
    private String productName;

    @NotNull(message = "{form.error.required}")
    private BigDecimal price;

    public ProductItemModel() {
    }

    public ProductItemModel(String productName, BigDecimal price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        ProductItemModel that = (ProductItemModel) o;
        return Objects.equals(this.productName, that.productName) &&
                Objects.equals(this.price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.productName, this.price);
    }

    @Override
    public String toString() {
        return "ProductItemModel{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
