/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.db.dao;

import com.rf.projectd.db.entity.User;
import java.util.List;
import javax.inject.Inject;
import org.mongodb.morphia.Datastore;

/**
 *
 * @author raz
 */
public class UserAccess {

    @Inject
    private Datastore ds;
    
    public void persistUser(User user){
        ds.save(user);
    }
    
    public List<User> getUsers(){
        return ds.createQuery(User.class).asList();
    }
}
