/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.discount.rs.response;

import com.rf.projectd.discount.entity.DiscountEntity;
import com.rf.projectd.user.entity.User;

/**
 *
 * @author XFODOR
 */
public class DiscountResponse {

    private String id;
    
    private String discountName;
    private String description;
    private String website;
    private Long price;
    private String userName;
    private DiscountType type = DiscountType.SELL;
    private String code;
    
    
    public DiscountResponse(DiscountEntity discount, User user) {
       this.id = discount.getId().toString();
       this.discountName = discount.getDiscountName();
       this.description = discount.getDescription();
       this.website = discount.getWebsite();       
       this.price = discount.getPrice();
       this.userName = user.getUserName();
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public DiscountType getType() {
        return type;
    }

    public void setType(DiscountType type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
