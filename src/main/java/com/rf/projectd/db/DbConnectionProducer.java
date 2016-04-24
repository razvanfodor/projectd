/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author raz
 */
@Singleton
public class DbConnectionProducer {

    private MongoClient mongo;
    private MongoDatabase db;
    private Datastore datastore;

    @Produces
    public MongoClient getConnection() {
        return mongo;
    }

    @Produces
    public MongoDatabase getDb() {
        return db;
    }
    
    @Produces
    public Datastore getDatastore() {
        return datastore;
    }

    @PostConstruct
    public void init() {
        String mongoIpAddress = "localhost";
        Integer mongoPort = 27017;
        mongo = new MongoClient(mongoIpAddress, mongoPort);
        db = mongo.getDatabase("projectD");

        initMorphia();
    }

    private void initMorphia() {
        final Morphia morphia = new Morphia();

        morphia.mapPackage("com.rf.projectd.db.entity");

        datastore = morphia.createDatastore(new MongoClient(), "projectD");
        datastore.ensureIndexes();
    }
}
