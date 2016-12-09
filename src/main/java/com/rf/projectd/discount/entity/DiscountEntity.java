/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.discount.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author XFODOR
 */
@Entity("discounts")
public class DiscountEntity implements Cloneable {
    
    @Id
    private ObjectId id;
    
    private Date creationDate;
    private String discountName;

    private String description;
    private String website;
    private String code;
    private Long price;
    private ObjectId creatorId;
    private List<Buyer> buyers;
    private List<String> tags;
    private Date expiryDate;

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

    public void setCreatorId(ObjectId id) {
        this.creatorId = id;
    }

    public ObjectId getCreatorId() {
        return creatorId;
    }

    public ObjectId getId() {
        return id;
    } 

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public DiscountEntity cloneDetached(){
        final DiscountEntity discount = new DiscountEntity();
        
        this.setCode(discount.getCode());
        this.setDiscountName(discount.getDiscountName());
        this.setWebsite(discount.getWebsite());
        this.setPrice(discount.getPrice());
        this.setCreatorId(discount.getCreatorId());
        
        return discount;
    }

    public List<Buyer> getBuyers() {
        if (buyers == null){
            buyers = new ArrayList<>();
        }
        return buyers;
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
}
    