/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.discount.rs;

import com.rf.projectd.common.RestResponseService;
import com.rf.projectd.discount.DiscountAccess;
import com.rf.projectd.discount.entity.DiscountEntity;
import com.rf.projectd.discount.entity.Buyer;
import com.rf.projectd.discount.rs.response.DiscountResponse;
import com.rf.projectd.discount.rs.response.DiscountType;
import com.rf.projectd.user.UserBE;
import com.rf.projectd.user.UserContext;
import com.rf.projectd.user.entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.types.ObjectId;

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
        discount.setCreatorId(userContext.getLoggedInUser().getId());
        discount.setCreationDate(new Date());
        discountAccess.save(discount);
    }

    @GET
    @Path("/getMy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCreatedByUser() {
        final List<DiscountEntity> discounts = discountAccess.getCreatedBy(userContext.getLoggedInUser().getId());
        return responseService.ok(transformToDiscountResponses(discounts));
    }

    @GET
    @Path("/getBought")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBoughtByUser() {
        final List<DiscountEntity> discounts = discountAccess.getBoughtBy(userContext.getLoggedInUser().getId());
        return responseService.ok(transformToDiscountResponses(discounts));
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("searchValue") String searchValue) {
        final List<DiscountEntity> discounts = discountAccess.search(searchValue, userContext.getLoggedInUser().getId());
        List<DiscountResponse> responses = transformToDiscountResponses(discounts);
        return responseService.ok(responses);
    }

    @GET
    @Path("/details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetails(@QueryParam("did") String discountId) {
        final DiscountEntity discount = discountAccess.getById(discountId);
        User user = userBe.getUserById(discount.getCreatorId());
        final DiscountResponse response = new DiscountResponse(discount, user);
        final User currentUser = userContext.getLoggedInUser();

        if (currentUser.equals(user)) {
            response.setType(DiscountType.OWNED);
            response.setCode(discount.getCode());
        } else if (isBuyer(currentUser, discount)) {
            response.setType(DiscountType.BOUGHT);
            response.setCode(discount.getCode());
        } else {
            response.setType(DiscountType.SELL);
        }
        return responseService.ok(response);
    }

    @PUT
    @Path("/buy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyDiscount(String discountId) {
        final DiscountEntity discount = discountAccess.getById(discountId);
        User buyer = userContext.getLoggedInUser();
        if (buyer.getdPoints() < discount.getPrice()) {
            throw new RuntimeException("Insuficient fonds.");
        }

        User seller = userBe.getUserById(discount.getCreatorId());
        final Long sellerPoints = seller.getdPoints() + discount.getPrice();
        seller.setdPoints(sellerPoints);

        Long userPoints = buyer.getdPoints() - discount.getPrice();
        buyer.setdPoints(userPoints);

        discount.getBuyers().add(new Buyer(buyer.getId(), new Date()));

        discountAccess.save(discount);
        userBe.persistUser(seller);
        userBe.persistUser(buyer);

        return responseService.ok(null);
    }

    private boolean isBuyer(User user, DiscountEntity discount) {
        return discount.getBuyers().contains(user.getId());
    }

    private List<DiscountResponse> transformToDiscountResponses(final List<DiscountEntity> discounts) {
        List<DiscountResponse> responses = new ArrayList(discounts.size());
        for (DiscountEntity discount : discounts) {
            User creatorUser = userBe.getUserById(discount.getCreatorId());
            final DiscountResponse discountResponse = new DiscountResponse(discount, creatorUser);
            final User loggedInUser = userContext.getLoggedInUser();
            if (!creatorUser.equals(loggedInUser)){
                for (Buyer buyer : discount.getBuyers()){
                    if (loggedInUser.getId().equals(buyer.getUserId())){
                        discountResponse.setBuyDate(buyer.getDate());
                    }
                }
            }
            
            responses.add(discountResponse);
        }
        return responses;
    }
}
