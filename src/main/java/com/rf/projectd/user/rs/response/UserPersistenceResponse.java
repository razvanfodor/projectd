/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.user.rs.response;

/**
 *
 * @author XFODOR
 */
public class UserPersistenceResponse {
    
    private String errorMessage;

    /**
     * Get the value of errorMessage
     *
     * @return the value of errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Set the value of errorMessage
     *
     * @param errorMessage new value of errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
