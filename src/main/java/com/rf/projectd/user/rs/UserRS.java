/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.user.rs;

import com.rf.projectd.user.UserBE;
import com.rf.projectd.user.entity.User;
import com.rf.projectd.user.rs.response.UserPersistenceResponse;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author raz
 */
@Stateless
@Path("/user")
public class UserRS {
    
    @Inject
    public UserBE userBe;

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public UserPersistenceResponse registerUser(User user){
        return userBe.createNewUser(user);
    } 
}
