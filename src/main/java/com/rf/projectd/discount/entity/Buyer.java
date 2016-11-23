/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.discount.entity;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author raz
 */
public class Buyer {
    private ObjectId userId;
    private Date date;

    public Buyer() {
    }

    public Buyer(ObjectId userId, Date date) {
        this.userId = userId;
        this.date = date;
    }
    
    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
