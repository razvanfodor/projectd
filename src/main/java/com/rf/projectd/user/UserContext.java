/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.user;

import com.rf.projectd.user.entity.User;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author XFODOR
 */
@RequestScoped
public class UserContext {

    private User loggedInUser;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    protected void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

}
