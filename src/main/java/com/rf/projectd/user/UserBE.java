/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.user;

import com.rf.projectd.user.entity.User;
import javax.inject.Inject;

/**
 *
 * @author XFODOR
 */
public class UserBE {
    @Inject
    private UserAccess userAccess;
    
    public void createNewUser(User user){
        userAccess.persistUser(user);
    }
}
