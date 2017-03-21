/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.common;

import com.mongodb.MongoClient;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import com.rf.projectd.common.config.Prop;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author raz
 */
@ApplicationScoped
public class DbConnectionProducer {
    private static final String MORPHIA_MAP_PACKAGE = "com.rf.projectd.db.entity";

    private MongoClient mongo;
    private Datastore datastore;
    
    @Inject
    @Prop("DATABASE_PORT")
    private Integer mongoPort;

    @Inject
    @Prop("DATABASE_HOST")
    private String databaseHostAddress;
    
    @Inject
    @Prop("DATASTORE")
    private String datastoreName;
    
    @Produces
    public MongoClient getConnection() {
        return mongo;
    }

    @Produces
    public Datastore getDatastore() {
        return datastore;
    }

    @PostConstruct
    public void init() {
        mongo = new MongoClient(databaseHostAddress, mongoPort);

        initMorphia();
    }

    private void initMorphia() {
        final Morphia morphia = new Morphia();

        morphia.mapPackage(MORPHIA_MAP_PACKAGE);

        datastore = morphia.createDatastore(mongo, datastoreName);
        datastore.ensureIndexes();
    }
}
