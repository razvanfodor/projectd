package com.rf.projectd.common;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author XFODOR
 */
public class RestResponseService {

    public Response ok() {
        return ok(null);
    }

    public Response ok(Object responseObject) {
        return getNoCacheResponseBuilder(Response.Status.OK).entity(responseObject).build();
    }

    public Response unauthorized() {
        return getNoCacheResponseBuilder(Response.Status.UNAUTHORIZED).build();
    }

    public Response badRequest(String message) {
        return getNoCacheResponseBuilder(Response.Status.BAD_REQUEST).entity(new ErrorMessage(message)).build();
    }

    private Response.ResponseBuilder getNoCacheResponseBuilder(Response.Status status) {
        CacheControl cc = new CacheControl();
        cc.setNoCache(true);
        cc.setMaxAge(-1);
        cc.setMustRevalidate(true);

        return Response.status(status).cacheControl(cc);
    }

    private static class ErrorMessage {

        private final String message;

        public ErrorMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
