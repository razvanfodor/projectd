/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.search.rs;

import com.rf.projectd.common.RestResponseService;
import java.util.Arrays;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author raz
 */
@Stateless
@Path("/search")
public class Search {
    
    @Inject
    private RestResponseService responseService;
    
    @GET
    @Path("/getHints")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHints(@PathParam("searchValue") String searchValue){
        return responseService.ok(Arrays.asList("a", "b", "c"));
    }
    
}
