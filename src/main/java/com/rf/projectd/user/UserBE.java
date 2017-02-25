/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.user;

import com.rf.projectd.user.entity.User;
import com.rf.projectd.user.entity.UserComment;
import com.rf.projectd.user.rs.request.NewCommentRequest;
import com.rf.projectd.user.rs.request.ProfileUpdateRequest;
import com.rf.projectd.user.rs.response.ProfileException;
import com.rf.projectd.user.rs.response.UserPersistenceResponse;
import java.util.Date;
import javax.inject.Inject;
import org.bson.types.ObjectId;
import com.rf.projectd.common.PasswordEncryptor;

/**
 *
 * @author XFODOR
 */
public class UserBE {

    private static final long DEFAULT_D_POINTS = 20L;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
            + "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Inject
    private UserAccess userAccess;

    @Inject
    private UserContext userContext;
    
    @Inject
    private PasswordEncryptor passwordEncryptor;

    public void createNewUser(User user) {
        validateProfile(user.getFirstName(), user.getLastName(), user.getEmail());
        validatePassword(user.getPassword());
        encryptPassword(user);
        
        final User foundUser = userAccess.getUserByName(user.getUserName());

        if (foundUser == null) {
            user.setdPoints(DEFAULT_D_POINTS);
            userAccess.persistUser(user);
        } else {
            throw new ProfileException("User already exists!");
        }
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
    
    public void updateUserProfile(ProfileUpdateRequest profileUpdate) throws ProfileException {
        final String firstName = profileUpdate.getFirstName();
        final String lastName = profileUpdate.getLastName();
        final String email = profileUpdate.getEmail();

        validateProfile(firstName, lastName, email);

        final User user = userContext.getLoggedInUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        persistUser(user);
    }

    private void validateProfile(final String firstName, final String lastName, final String email) throws ProfileException {
        if (firstName == null || firstName.length() < 2) {
            throw new ProfileException("First name has to be longer than 2 characters.");
        }
        if (lastName == null || lastName.length() < 2) {
            throw new ProfileException("Last name has to be longer than 2 characters.");
        }
        if (email == null || !email.matches(EMAIL_PATTERN)) {
            throw new ProfileException("The email is invalid.");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 2){
            throw new ProfileException("The password is too short.");
        }
    }

    private void encryptPassword(User user) {
        user.setPassword(passwordEncryptor.encrypt(user.getPassword()));
    }
}
