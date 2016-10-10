/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.fe.rs;

import com.rf.projectd.db.dao.UserAccess;
import com.rf.projectd.db.entity.User;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author raz
 */
@RequestScoped
@Path("/user")
public class UserRS {
    @Inject
    private UserAccess userAccess;

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public void registerUser(User user){
        userAccess.persistUser(user);
    }
    
    @GET
    @Path("/getAll")
    public List<User> getAllUsers(){
        return userAccess.getUsers();
    }
}
