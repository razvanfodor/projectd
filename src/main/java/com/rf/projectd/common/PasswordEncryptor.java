/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.common;

/**
 *
 * @author raz
 */
public interface PasswordEncryptor {
    String encrypt(String password);
    boolean checkPassword(String inputPassword, String targetPassword);
}
