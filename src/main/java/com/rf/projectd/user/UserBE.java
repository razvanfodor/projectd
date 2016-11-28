/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.user;

import com.rf.projectd.user.entity.User;
import com.rf.projectd.user.entity.UserComment;
import com.rf.projectd.user.rs.request.NewCommentRequest;
import com.rf.projectd.user.rs.response.UserPersistenceResponse;
import java.util.Date;
import javax.inject.Inject;
import org.bson.types.ObjectId;

/**
 *
 * @author XFODOR
 */
public class UserBE {

    private static final long DEFAULT_D_POINTS = 20L;

    @Inject
    private UserAccess userAccess;

    @Inject
    private UserContext userContext;

    public UserPersistenceResponse createNewUser(User user) {
        final User foundUser = userAccess.getUserByName(user.getUserName());

        final UserPersistenceResponse response = new UserPersistenceResponse();
        if (foundUser == null) {
            user.setdPoints(DEFAULT_D_POINTS);
            userAccess.persistUser(user);
        } else {
            response.setErrorMessage("User already exists!");
        }
        return response;
    }

    public User getUserById(ObjectId userId) {
        return userAccess.getuserById(userId);
    }

    public void persistUser(User user) {
        userAccess.persistUser(user);
    }

    public void addNewComment(NewCommentRequest comment) {
        final User user = userAccess.getuserById(new ObjectId(comment.getUserId()));
        final User loggedInUser = userContext.getLoggedInUser();
        user.getComments().add(new UserComment(loggedInUser.getId(),
                comment.getText(), 
                comment.getRating(), 
                comment.getSummary(), 
                new Date()));
        Integer ratingSum = user.getComments().stream()
                    .reduce(0, (sum, comm) -> sum += comm.getRating(), (sum1, sum2) -> sum1 + sum2 );
        user.setRatingPoints(Math.round(ratingSum / user.getComments().size()));
        persistUser(user);
    }
}
