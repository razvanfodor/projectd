/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd;

import com.rf.projectd.fe.rs.HomeControler;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author raz
 */
@ApplicationPath("service")
public class ProjectD extends Application {
//
//    @Override
//    public Set<Class<?>> getClasses() {
//        final Set<Class<?>> resources = new java.util.HashSet<>();
//        // following code can be used to customize Jersey 2.0 JSON provider:
//        try {
//            final Class jsonProvider = Class.forName("org.glassfish.jersey.jackson.JacksonFeature");
//            resources.add(jsonProvider);
//        } catch (final ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(getClass().getName())
//                    .log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        addRestResourceClasses(resources);
//        return resources;
//    }
//
//    /**
//     * Add your own resources here.
//     */
//    private void addRestResourceClasses(Set<Class<?>> resources) {
//        resources.add(com.rf.main.fe.rs.HomeControler.class);
//    }
}
