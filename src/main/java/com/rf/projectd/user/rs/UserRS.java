/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.user.rs;

import com.rf.projectd.common.PDException;
import com.rf.projectd.user.rs.request.ProfileUpdateRequest;
import com.rf.projectd.user.rs.response.UserProfileResponse;
import com.rf.projectd.user.rs.request.NewCommentRequest;
import com.rf.projectd.common.RestResponseService;
import com.rf.projectd.user.rs.response.UserDetailsResponse;
import com.rf.projectd.user.UserBE;
import com.rf.projectd.user.UserContext;
import com.rf.projectd.user.entity.User;
import com.rf.projectd.user.rs.response.UserCommentResponse;
import com.rf.projectd.user.rs.response.UserPersistenceResponse;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

    @Inject
    private UserContext userContext;

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response registerUser(User user) {
        try{
            userBe.createNewUser(user);
        }
        catch(PDException e){
            return responseService.badRequest(e.getMessage());
        }
        return responseService.ok();
    }

    @GET
    @Path("/details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDetails(@QueryParam("uid") String userId) {
        final ObjectId userIdObj;
        if (userId == null || userId.isEmpty()) {
            userIdObj = userContext.getLoggedInUser().getId();
        } else {
            userIdObj = new ObjectId(userId);
        }
        final User user = userBe.getUserById(userIdObj);

        return responseService.ok(getUserDetails(user));
    }

    @GET
    @Path("/profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserProfile() {
        final User user = userContext.getLoggedInUser();
        final UserProfileResponse profile = new UserProfileResponse(user);
        user.getComments().forEach(comment -> {
            profile.getComments()
                    .add(new UserCommentResponse(comment, userBe.getUserById(comment.getCommenterId()).getUserName()));
        });
        return responseService.ok(profile);
    }

    @PUT
    @Path("/profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserProfile(ProfileUpdateRequest profileUpdate) {
        try{
            userBe.updateUserProfile(profileUpdate);
        }
        catch(PDException e){
            return responseService.badRequest(e.getMessage());
        }
        return responseService.ok();
    }

    @PUT
    @Path("/addComment")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public void addComment(NewCommentRequest comment) {
        userBe.addNewComment(comment);
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
                    .add(new UserCommentResponse(userComment, userName));
        });

        return details;
    }
}
