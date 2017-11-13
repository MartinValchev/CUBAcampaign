package com.company.salesnew.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "SALESNEW_PRODUCT")
@Entity(name = "salesnew$Product")
public class Product extends StandardEntity {
    private static final long serialVersionUID = 2615743118848744891L;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "PRICE", nullable = false, precision = 19, scale = 3)
    protected BigDecimal price;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }


}