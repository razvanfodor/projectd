/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.user.rs.response;

import com.rf.projectd.common.PDException;

/**
 *
 * @author raz
 */
public class ProfileException extends PDException {
    public ProfileException(String message) {
        super(message);
    }
}
