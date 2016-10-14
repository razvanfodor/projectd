/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.user;

import com.rf.projectd.auth.AuthenticationAccess;
import com.rf.projectd.auth.AuthenticationBE;
import com.rf.projectd.auth.entity.AuthenticationSession;
import com.rf.projectd.user.entity.User;
import java.io.IOException;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author XFODOR
 */
@Provider
@PreMatching
public class UserContextFilter implements ContainerRequestFilter {
    
    @Inject
    private UserAccess userAccess; 
    
    @Inject
    private AuthenticationAccess authAccess;
    
    @Inject
    private UserContext userContext;

    @Override
    public void filter(ContainerRequestContext requestCtx) throws IOException {
        String authToken = requestCtx.getHeaderString(AuthenticationBE.AUTH_TOKEN);
        if (authToken != null && !authToken.isEmpty()){
            final AuthenticationSession authSession = authAccess.getByAuthToken(authToken);
            
            final User user = userAccess.getUserByName(authSession.getUserName());
            userContext.setLoggedInUser(user);
        }
    }

}
