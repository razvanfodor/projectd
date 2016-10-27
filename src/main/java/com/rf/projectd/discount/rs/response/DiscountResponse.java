/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.discount.rs.response;

import com.rf.projectd.discount.entity.DiscountEntity;

/**
 *
 * @author XFODOR
 */
public class DiscountResponse {

    private String id;
    
    private String discountName;
    private String website;
    private Long price;
    
    public DiscountResponse(DiscountEntity discount) {
       this.id = discount.getId().toString();
       this.discountName = discount.getDiscountName();
       this.website = discount.getWebsite();       
       this.price = discount.getPrice();
    }

    public String getId() {
        return id;
    }

    public String getDiscountName() {
        return discountName;
    }

    public String getWebsite() {
        return website;
    }

    public Long getPrice() {
        return price;
    }
}
