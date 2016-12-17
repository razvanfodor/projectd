/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.discount.rs;

import com.rf.projectd.common.PDException;
import com.rf.projectd.common.RestResponseService;
import com.rf.projectd.discount.DiscountAccess;
import com.rf.projectd.discount.entity.DiscountEntity;
import com.rf.projectd.discount.entity.Buyer;
import com.rf.projectd.discount.rs.request.DiscountRequest;
import com.rf.projectd.discount.rs.response.DiscountResponse;
import com.rf.projectd.discount.rs.response.DiscountType;
import com.rf.projectd.user.UserBE;
import com.rf.projectd.user.UserContext;
import com.rf.projectd.user.entity.User;
import java.util.ArrayList;
import java.util.Calendar;
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
 * Regular Expression for URL validation Author: Diego Perini Updated:
 * 2010/12/05 License: MIT Copyright (c) 2010-2013 DiegoPerini
 * (http://www.iport.it)
 *
 * @author XFODOR
 */
@Stateless
@Path("/discount")
public class DiscountRS {

    private final String URL_REGEX = "^"
            + // protocol identifier
            "(?:(?:https?|ftp)://)"
            + // user:pass authentication
            "(?:\\S+(?::\\S*)?@)?"
            + "(?:"
            + // IP address exclusion
            // private & local networks
            "(?!(?:10|127)(?:\\.\\d{1,3}){3})"
            + "(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})"
            + "(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})"
            + // IP address dotted notation octets
            // excludes loopback network 0.0.0.0
            // excludes reserved space >= 224.0.0.0
            // excludes network & broacast addresses
            // (first & last IP address of each class)
            "(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])"
            + "(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}"
            + "(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))"
            + "|"
            + // host name
            "(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)"
            + // domain name
            "(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*"
            + // TLD identifier
            "(?:\\.(?:[a-z\\u00a1-\\uffff]{2,}))"
            + // TLD may end with dot
            "\\.?"
            + ")"
            + // port number
            "(?::\\d{2,5})?"
            + // resource path
            "(?:[/?#]\\S*)?"
            + "$";
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
    public Response saveNew(DiscountRequest discountReq) {
        final String validationErrorMessage = validate(discountReq);
        if (validationErrorMessage != null) {
            return responseService.badRequest(validationErrorMessage);
        }
        DiscountEntity discount = toDiscountEntity(discountReq, new DiscountEntity());

        discount.setCreatorId(userContext.getLoggedInUser().getId());
        discount.setCreationDate(new Date());
        discountAccess.save(discount);
        return responseService.ok();
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateDiscout(DiscountRequest discountReq) {
        final String validationErrorMessage = validate(discountReq);
        if (validationErrorMessage != null) {
            return responseService.badRequest(validationErrorMessage);
        }

        DiscountEntity discount = discountAccess.getById(discountReq.getId());
        discount = toDiscountEntity(discountReq, discount);
        discountAccess.save(discount);
        return responseService.ok();
    }

    @GET
    @Path("/getCreatedBy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCreatedBy(@QueryParam("uid") String userId) {
        return getCreatedBy(new ObjectId(userId));
    }

    @GET
    @Path("/getMy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCreatedByLoggedInUser() {
        return getCreatedBy(userContext.getLoggedInUser().getId());
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
    public Response search(@QueryParam("searchValue") String searchValue,
            @QueryParam("startIndex") Integer startIndex,
            @QueryParam("numberEntriesPerPage") Integer numberEntriesPerPage,
            @QueryParam("sortPredicate") String sortPredicate,
            @QueryParam("sortReverse") Boolean sortReverse) {
        sortReverse = sortReverse != null ? sortReverse : false;
        final ObjectId userId = userContext.getLoggedInUser().getId();
        final List<DiscountEntity> discounts = discountAccess.search(searchValue,
                startIndex,
                numberEntriesPerPage,
                sortPredicate,
                sortReverse,
                userId);
        List<DiscountResponse> responses = transformToDiscountResponses(discounts);
        final long searchCount = discountAccess.searchCount(searchValue, userId);

        return responseService.ok(new SearchResult(responses, searchCount));
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
        } else if (!discount.isSingleSell() || discount.getBuyers().size() < 1) {
            response.setType(DiscountType.SELL);
        } else {
            throw new PDException("No rights to discount.");
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
            throw new PDException("Insuficient fonds.");
        }
        if (discount.isSingleSell() && !discount.getBuyers().isEmpty()){
            throw new PDException("Discount is already sold.");
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

        return responseService.ok();
    }

    private boolean isBuyer(User user, DiscountEntity discount) {
        return discount.getBuyers().stream().filter(buyer -> user.getId().equals(buyer.getUserId())).findFirst().isPresent();
    }

    private List<DiscountResponse> transformToDiscountResponses(final List<DiscountEntity> discounts) {
        List<DiscountResponse> responses = new ArrayList(discounts.size());
        for (DiscountEntity discount : discounts) {
            User creatorUser = userBe.getUserById(discount.getCreatorId());
            final DiscountResponse discountResponse = new DiscountResponse(discount, creatorUser);
            final User loggedInUser = userContext.getLoggedInUser();
            if (!creatorUser.equals(loggedInUser)) {
                for (Buyer buyer : discount.getBuyers()) {
                    if (loggedInUser.getId().equals(buyer.getUserId())) {
                        discountResponse.setBuyDate(buyer.getDate());
                    }
                }
            }

            responses.add(discountResponse);
        }
        return responses;
    }

    private Response getCreatedBy(ObjectId userId) {
        final List<DiscountEntity> discounts = discountAccess.getCreatedBy(userId);
        return responseService.ok(transformToDiscountResponses(discounts));
    }

    private DiscountEntity toDiscountEntity(DiscountRequest discountReq, DiscountEntity discount) {
        discount.setDiscountName(discountReq.getDiscountName());
        discount.setDescription(discountReq.getDescription());
        discount.setCode(discountReq.getCode());
        discount.setPrice(discountReq.getPrice());
        discount.setWebsite(discountReq.getWebsite());
        discount.getTags().clear();
        discount.getTags().addAll(discountReq.getTags());
        discount.setExpiryDate(discountReq.getExpiryDate());
        discount.setSingleSell(discountReq.isSingleSell());
        return discount;
    }

    private String validate(DiscountRequest discount) {
        if (discount.getDiscountName() == null || discount.getDiscountName().isEmpty()) {
            return "Invalid Name.";
        }
        if (discount.getWebsite() == null || discount.getWebsite().isEmpty() || !discount.getWebsite().matches(URL_REGEX)) {
            return "Invalid Store URL.";
        }
        if (discount.getCode() == null || discount.getCode().isEmpty()) {
            return "Invalid discount code.";
        }
        if (discount.getExpiryDate() == null || discount.getExpiryDate().before(getTomorrow())) {
            return "Invalid expiration date.";
        }
        if (discount.getPrice() == null || discount.getPrice() < 0) {
            return "Invalid Price.";
        }
        return null;
    }

    private static Date getTomorrow() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }
}
