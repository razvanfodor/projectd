/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.net.UnknownHostException;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;

/**
 *
 * @author raz
 */
@Singleton
public class DbConnectionProducer {

    private MongoClient mongo;
    private MongoDatabase db;

    @Produces
    public MongoClient getConnection() {
        return mongo;
    }
    
    
    @Produces
    public MongoDatabase getDb() {
        return db;
    }
    
    @PostConstruct
    public void init() {
        String mongoIpAddress = "localhost";
        Integer mongoPort = 27017;
        mongo = new MongoClient(mongoIpAddress, mongoPort);
        db  =  mongo.getDatabase("projectD");
    }
}
