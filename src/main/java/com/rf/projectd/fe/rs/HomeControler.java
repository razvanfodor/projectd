/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.fe.rs;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.rf.projectd.fe.rs.response.Hello;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.bson.Document;

/**
 *
 * @author raz
 */
@Path("/home")
@RequestScoped
public class HomeControler {

    @Inject
    private MongoDatabase db;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Hello get() {
        MongoCollection<Document> users = db.getCollection("users");

        String result = "";
        for (Document d : users.find()) {
            result += d.toJson();
        }


        return new Hello(result);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addUser")
    public void createNewUser(String userName) {
        db.getCollection("users").insertOne((Document) new Document("name", userName));
    }
}
