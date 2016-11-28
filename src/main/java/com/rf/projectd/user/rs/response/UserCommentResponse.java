/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.user.rs.response;

import com.rf.projectd.user.entity.UserComment;
import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author raz
 */
public class UserCommentResponse {
    
    private String userId;
    private String userName;
    private Integer rating;
    private String comment;
    private String summary;
    private Date date;

    public UserCommentResponse() {
    }


    public UserCommentResponse(UserComment comment, String commenterName){
        this.userId = comment.getCommenterId().toString();
        this.userName = commenterName;
        this.rating = comment.getRating();
        this.comment = comment.getComment();
        this.summary = comment.getSummary();
        this.date = comment.getDate();
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}