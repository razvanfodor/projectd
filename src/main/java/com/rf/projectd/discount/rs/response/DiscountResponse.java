/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.discount.rs.response;

import com.rf.projectd.discount.entity.DiscountEntity;
import com.rf.projectd.user.entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author XFODOR
 */
public class DiscountResponse {

    private String id;
    
    private Date creationDate;
    private Date buyDate;
    private String discountName;
    private String description;
    private String website;
    private Long price;
    private String userName;
    private String userId;
    private DiscountType type = DiscountType.SELL;
    private String code;
    private List<String> tags;
    private Date expiryDate;
    private Boolean singleSell = false;
    private Long sellTimes = 0L;

    
    public DiscountResponse(DiscountEntity discount, User user) {
       this.id = discount.getId().toString();
       this.creationDate = discount.getCreationDate();
       this.discountName = discount.getDiscountName();
       this.description = discount.getDescription();
       this.website = discount.getWebsite();       
       this.price = discount.getPrice();
       this.userName = user.getUserName();
       this.userId = user.getId().toString();
       this.expiryDate = discount.getExpiryDate();
       this.getTags().addAll(discount.getTags());
       this.singleSell = discount.isSingleSell();
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getTags() {
        if (tags == null){
            tags = new ArrayList();
        }
        return tags;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getSingleSell() {
        return singleSell;
    }

    public void setSingleSell(Boolean singleSell) {
        this.singleSell = singleSell;
    }
    
    public Long getSellTimes() {
        return sellTimes;
    }

    public void setSellTimes(Long sellTimes) {
        this.sellTimes = sellTimes;
    }
}
