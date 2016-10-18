/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.discount.rs;

import com.rf.projectd.common.RestResponseService;
import com.rf.projectd.discount.DiscountAccess;
import com.rf.projectd.discount.entity.DiscountEntity;
import com.rf.projectd.user.UserContext;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author XFODOR
 */
@Stateless
@Path("/discount")
public class Discount {
    
    @Inject
    private DiscountAccess discountAccess;
    
    @Inject
    private UserContext userContext;
    
    @Inject
    private RestResponseService responseService;
    
    @POST
    @Path("/saveNew")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public void saveNew(DiscountEntity discount){
        discount.setUserId(userContext.getLoggedInUser().getId());
        discountAccess.save(discount);
    }
    
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getForUser(){
        final List<DiscountEntity> discounts = discountAccess.getAllForUser(userContext.getLoggedInUser().getId());
        
        return responseService.ok(discounts);
    }
    
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchs(@QueryParam("searchValue") String searchValue){
        final List<DiscountEntity> discounts = discountAccess.search(searchValue, userContext.getLoggedInUser().getId());
        
        return responseService.ok(discounts);
    }
}
