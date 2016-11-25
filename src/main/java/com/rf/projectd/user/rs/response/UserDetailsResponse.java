/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.user.rs.response;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raz
 */
public class UserDetailsResponse {
    private String userName;
    private Integer ratingPoints;
    private List<UserCommentResponse> comments;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public Integer getRatingPoints() {
        return ratingPoints;
    }

    public void setRatingPoints(Integer ratingPoints) {
        this.ratingPoints = ratingPoints;
    }

    public List<UserCommentResponse> getComments() {
        if (comments == null){
            comments = new ArrayList<>();
        }
        return comments;
    }

    public void setComments(List<UserCommentResponse> comments) {
        this.comments = comments;
    }
}
