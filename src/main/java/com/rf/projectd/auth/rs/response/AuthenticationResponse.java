/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.auth.rs.response;

/**
 *
 * @author XFODOR
 */
public class AuthenticationResponse {
   
    private String authToken;

    /**
     * Get the value of authToken
     *
     * @return the value of authToken
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Set the value of authToken
     *
     * @param authToken new value of authToken
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

}
