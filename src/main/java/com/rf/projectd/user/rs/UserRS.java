/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.user.rs;

import com.rf.projectd.common.RestResponseService;
import com.rf.projectd.user.rs.response.UserDetailsResponse;
import com.rf.projectd.user.UserBE;
import com.rf.projectd.user.entity.User;
import com.rf.projectd.user.rs.response.UserCommentResponse;
import com.rf.projectd.user.rs.response.UserPersistenceResponse;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.types.ObjectId;

/**
 *
 * @author raz
 */
@Stateless
@Path("/user")
public class UserRS {
    
    @Inject
    public UserBE userBe;

    @Inject
    private RestResponseService responseService;

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public UserPersistenceResponse registerUser(User user){
        return userBe.createNewUser(user);
    } 

    @GET
    @Path("/details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDetails(@QueryParam("uid") String userId){
        final User user = userBe.getUserById(new ObjectId(userId));
        
        return responseService.ok(getUserDetails(user));
    } 

    private UserDetailsResponse getUserDetails(User user) {
        final UserDetailsResponse details = new UserDetailsResponse();
        
        details.setUserName(user.getUserName());
        details.setRatingPoints(user.getRatingPoints());

        user.getComments().forEach(userComment -> {
            String userName = userBe
                    .getUserById(userComment.getCommenterId())
                    .getUserName();
            details.getComments()
                    .add(new UserCommentResponse( userComment, userName));
        });
        
        return details;
    }
}
