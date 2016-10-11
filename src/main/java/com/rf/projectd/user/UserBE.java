/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.user;

import com.rf.projectd.user.entity.User;
import com.rf.projectd.user.rs.response.UserPersistenceResponse;
import javax.inject.Inject;

/**
 *
 * @author XFODOR
 */
public class UserBE {
    
    @Inject
    private UserAccess userAccess;
    
    public UserPersistenceResponse createNewUser(User user) {
        final User foundUser = userAccess.getUserByName(user.getUserName());
        
        final UserPersistenceResponse response = new UserPersistenceResponse();
        if (foundUser == null) {
            userAccess.persistUser(user);
        } else {
            response.setErrorMessage("User already exists!");
        }
        return response;
    }
}
