/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.auth;

import com.rf.projectd.auth.entity.AuthenticationSession;
import com.rf.projectd.user.UserAccess;
import com.rf.projectd.user.entity.User;
import java.util.Date;
import java.util.UUID;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;

/**
 * 
 * @author XFODOR
 */
public class AuthenticationBE {
    public static final String AUTH_TOKEN = "auth_token";
    
    @Inject
    private UserAccess userAccess;
    
    @Inject
    private AuthenticationAccess authAccess;
    
    public String login(String userName, String password) throws LoginException{
        final User user = userAccess.getUserByName(userName);
        if (user != null && user.getPassword().equals(password)){
            AuthenticationSession session = authAccess.getByUserName(userName);
            if (session == null){
                session = new AuthenticationSession();
                session.setUserName(userName);
            }
            final String authToken = UUID.randomUUID().toString();

            session.setLoginDate(new Date());
            session.setAuthKey(authToken);
            
            authAccess.persist(session);
            
            return authToken;
        }
        else{
            throw new LoginException("User or password is wrong!");
        }
    }
    
    public void logout(String authToken){
        authAccess.remove(authToken);
    }
    
    public boolean isTokenValid(String authToken){
        AuthenticationSession session = authAccess.getByAuthToken(authToken);
        return session != null;
    }
}
