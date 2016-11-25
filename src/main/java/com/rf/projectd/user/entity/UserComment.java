/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.user.entity;

import org.bson.types.ObjectId;

/**
 *
 * @author raz
 */
public class UserComment {

    private ObjectId commenterId;
    private String comment;
    private Integer rating;

    public UserComment() {
    }

    public UserComment(ObjectId commenterId, String comment, Integer rating) {
        this.commenterId = commenterId;
        this.comment = comment;
        this.rating = rating;
    }
    
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public ObjectId getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(ObjectId commenterId) {
        this.commenterId = commenterId;
    }
}
