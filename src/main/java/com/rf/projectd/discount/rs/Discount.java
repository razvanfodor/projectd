/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.discount.rs;

import com.rf.projectd.common.RestResponseService;
import com.rf.projectd.discount.DiscountAccess;
import com.rf.projectd.discount.entity.DiscountEntity;
import com.rf.projectd.discount.rs.response.DiscountResponse;
import com.rf.projectd.user.UserBE;
import com.rf.projectd.user.UserContext;
import com.rf.projectd.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
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

    @Inject
    private UserBE userBe;

    @POST
    @Path("/saveNew")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public void saveNew(DiscountEntity discount) {
        discount.setUserId(userContext.getLoggedInUser().getId());
        discountAccess.save(discount);
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getForUser() {
        final List<DiscountEntity> discounts = discountAccess.getAllForUser(userContext.getLoggedInUser().getId());

        return responseService.ok(discounts);
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchs(@QueryParam("searchValue") String searchValue) {
        final List<DiscountEntity> discounts = discountAccess.search(searchValue, userContext.getLoggedInUser().getId());
        List<DiscountResponse> responses = new ArrayList(discounts.size());
        for (DiscountEntity discount : discounts) {
            User user = userBe.getUserById(discount.getUserId());
            responses.add(new DiscountResponse(discount, user));
        }
        return responseService.ok(responses);
    }

    @GET
    @Path("/details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetails(@QueryParam("did") String discountId) {
        final DiscountEntity discount = discountAccess.getById(discountId);
        User user = userBe.getUserById(discount.getUserId());
        final DiscountResponse response = new DiscountResponse(discount, user);
        return responseService.ok(response);
    }
}
