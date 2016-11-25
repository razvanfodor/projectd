/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.user.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author raz
 */
@Entity("users")
public class User {
    @Id
    private ObjectId id;
    
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private Long dPoints;
    private Integer ratingPoints; // 1..10
    private List<UserComment> comments;
    
    //TODO this has to be encrypted
    private String password;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fistName) {
        this.firstName = fistName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<UserComment> getComments() {
        if (comments == null){
            comments = new ArrayList();
        }
        return comments;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        if (id != null ){
            hash += id.hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
 