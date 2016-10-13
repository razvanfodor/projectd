/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.discount.rs;

import com.rf.projectd.discount.DiscountAccess;
import com.rf.projectd.discount.entity.DiscountEntity;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author XFODOR
 */
@Stateless
@Path("/discount")
public class Discount {
    
    @Inject
    private DiscountAccess discountAccess;
    
    @POST
    @Path("/saveNew")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public void saveNew(DiscountEntity discount){
        discountAccess.save(discount);
    }
}
