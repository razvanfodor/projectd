/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.common;

import javax.enterprise.inject.Produces;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author raz
 */
public class PasswordEncryptorProducer {

    @Produces
    public PasswordEncryptor producePasswordVerifier(){
        return new PasswordEncryptor() {
            
            @Override
            public String encrypt(String password) {
                return new BasicPasswordEncryptor().encryptPassword(password);
            }
            
            @Override
            public boolean checkPassword(String inputPassword, String dbPassword) {
                final BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
                return encryptor.checkPassword(inputPassword, dbPassword);
            }
        }; 
    }
}
