/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.discount.entity;

import org.mongodb.morphia.annotations.Entity;

/**
 *
 * @author XFODOR
 */
@Entity("discounts")
public class DiscountEntity {
    
    private String discountName;

    private String website;
    private String code;
    private Long price;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    
    /**
     * Get the value of website
     *
     * @return the value of website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Set the value of website
     *
     * @param website new value of website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    
    /**
     * Get the value of discountName
     *
     * @return the value of discountName
     */
    public String getDiscountName() {
        return discountName;
    }

    /**
     * Set the value of discountName
     *
     * @param discountName new value of discountName
     */
    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

}
