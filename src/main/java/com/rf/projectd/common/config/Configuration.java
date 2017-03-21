/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.common.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * @author raz
 */
@Singleton
public class Configuration {

    private static final String GLOBAL_PROPERTIES_FILE = "global.properties";
    private static final String LOCAL_PROPERTIES_FILE = "/.projectd/local.properties";

    private Properties properties;

    @PostConstruct
    public void readConfig() {
        properties = new Properties();
        readGlobalPropertiesFile();
        readLocalPropertiesFile();
    }

    @Prop
    @Produces
    public String produceString(InjectionPoint injectionPoint) {
        String propertyName = injectionPoint.getAnnotated().getAnnotation(Prop.class).value();
        return properties.getProperty(propertyName);
    }

    @Prop
    @Produces
    public Long produceLong(InjectionPoint injectionPoint) {
        String propertyName = injectionPoint.getAnnotated().getAnnotation(Prop.class).value();
        return Long.parseLong(properties.getProperty(propertyName));
    }

    @Prop
    @Produces
    public Integer produceInteger(InjectionPoint injectionPoint) {
        String propertyName = injectionPoint.getAnnotated().getAnnotation(Prop.class).value();
        return Integer.parseInt(properties.getProperty(propertyName));
    }

    private void readGlobalPropertiesFile() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(GLOBAL_PROPERTIES_FILE)) {
            if (input == null) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, "Properties file not found!");
            }
            properties = new Properties();
            properties.load(input);
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void readLocalPropertiesFile() {
        String home = System.getProperty("user.home");
        File f = new File(home + LOCAL_PROPERTIES_FILE);
        try (FileInputStream fin = new FileInputStream(f)) {
            properties.load(fin);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
