/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.auth;

import com.rf.projectd.auth.entity.AuthenticationSession;
import javax.inject.Inject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author XFODOR
 */
public class AuthenticationAccess {
    @Inject
    private Datastore ds;

    public AuthenticationSession getByUserName(String userName) {
        return ds.createQuery(AuthenticationSession.class)
                .field("userName")
                .equalIgnoreCase(userName)
                .get();
    }

    public void persist(AuthenticationSession session) {
        ds.save(session);
    }

    public void remove(String authToken) {
        Query<AuthenticationSession> query = ds.createQuery(AuthenticationSession.class)
                .field("authKey")
                .equal(authToken);
        ds.delete(query);
    }

    public AuthenticationSession getByAuthToken(String authToken) {
        return ds.createQuery(AuthenticationSession.class)
                .field("authKey")
                .equal(authToken)
                .get();
    }
}
