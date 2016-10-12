/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.auth.rs;

import com.rf.projectd.common.RestResponseService;
import com.rf.projectd.auth.AuthenticationBE;
import com.rf.projectd.auth.rs.response.AuthenticationResponse;
import com.rf.projectd.user.entity.User;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author XFODOR
 */
@Stateless
@Path("/authentication")
public class AuthenticationRS {

    @Inject
    private AuthenticationBE authBe;
    @Inject
    private RestResponseService responseService;
    
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response login(User user){
        try {
            final String authToken = authBe.login(user.getUserName(), user.getPassword());
            final AuthenticationResponse resp = new AuthenticationResponse();
            resp.setAuthToken(authToken);
            return responseService.ok(resp);
        } catch (LoginException ex) {
            return responseService.unauthorized();
        }
    }
    
    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public void logout(@Context HttpHeaders httpHeaders){
        authBe.logout(httpHeaders.getHeaderString(AuthenticationBE.AUTH_TOKEN));
    }
}
