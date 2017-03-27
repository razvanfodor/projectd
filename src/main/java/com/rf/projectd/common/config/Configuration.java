/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.common.config;

import com.rf.projectd.common.log.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import org.apache.logging.log4j.Logger;

/**
 * @author raz
 */
@Singleton
public class Configuration {

    private static final String GLOBAL_PROPERTIES_FILE = "global.properties";
    private static final String LOCAL_PROPERTIES_FILE = "/.projectd/local.properties";

    @Inject
    @Log
    private Logger logger;

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
                logger.error("Can't read global properties file.");
            }
            properties = new Properties();
            properties.load(input);
            logger.info("Global properties file loaded.");
        } catch (IOException ex) {
            logger.error("Can't read global properties file.", ex);
        }
    }

    private void readLocalPropertiesFile() {
        String home = System.getProperty("user.home");
        File f = new File(home + LOCAL_PROPERTIES_FILE);
        try (FileInputStream fin = new FileInputStream(f)) {
            properties.load(fin);
            logger.info("Local properties file loaded");
        } catch (FileNotFoundException ex) {
            logger.info("Can't read local properties file. File not found.", ex);
        } catch (IOException ex) {
            logger.info("Can't read local properties file", ex);
        }
    }

}
