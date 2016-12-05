/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.user.rs.response;

import com.rf.projectd.user.entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XFODOR
 */
public class UserProfileResponse {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private Long dPoints;
    private Integer ratingPoints;
    private List<UserCommentResponse> comments;
    
    public UserProfileResponse(User user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
        userName = user.getUserName();
        email = user.getEmail();
        dPoints = user.getdPoints();
        ratingPoints = user.getRatingPoints();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getdPoints() {
        return dPoints;
    }

    public void setdPoints(Long dPoints) {
        this.dPoints = dPoints;
    }

    public Integer getRatingPoints() {
        return ratingPoints;
    }

    public void setRatingPoints(Integer ratingPoints) {
        this.ratingPoints = ratingPoints;
    }

    public List<UserCommentResponse> getComments() {
        if (comments == null){
            comments = new ArrayList();
        }
        return comments;
    }    
}
