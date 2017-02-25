/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.auth;

import java.io.IOException;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author XFODOR
 */
@Provider
@PreMatching
public class RestRequestFilter implements ContainerRequestFilter {
    
    private final static Logger log = Logger.getLogger( RestRequestFilter.class.getName() );
    private final static String[] UNAUTHENGICATED_PATHS = {"authentication/login", "user/register", "discount/search", "discount/details"}; 

    @Inject
    private AuthenticationBE authBe;
    
    @Override
    public void filter( ContainerRequestContext requestCtx ) throws IOException {
        String path = getRequestPath(requestCtx);
        log.info( "Filtering request path: " + path );

        // IMPORTANT!!! First, Acknowledge any pre-flight test from browsers for this case before validating the headers (CORS stuff)
        if ( requestCtx.getRequest().getMethod().equals( "OPTIONS" ) ) {
            requestCtx.abortWith( Response.status( Response.Status.OK ).build() );

            return;
        }
        
        if ( isAuthenticationRequired(path)) {
            String authToken = requestCtx.getHeaderString( AuthenticationBE.AUTH_TOKEN );
            if (!authBe.isTokenValid(authToken)){
                requestCtx.abortWith(Response.status( Response.Status.UNAUTHORIZED ).build());
            }
        }
    }

    private String getRequestPath(ContainerRequestContext requestCtx) {
        String path = requestCtx.getUriInfo().getPath();
        if (path.startsWith("/")){
            path = path.substring(1);
        }
        return path;
    }

    private static boolean isAuthenticationRequired(String path) {
        for (String unauthPath : UNAUTHENGICATED_PATHS){
            if (path.startsWith(unauthPath)){
                return false;
            }
        }
        return true;
    }
    
}
