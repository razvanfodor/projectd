/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.discount;

import com.rf.projectd.discount.entity.DiscountEntity;
import java.util.List;
import javax.inject.Inject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author XFODOR
 */
public class DiscountAccess {

    @Inject
    private Datastore ds;

    public void save(DiscountEntity discount) {
        ds.save(discount);
    }

    public List<DiscountEntity> getCreatedBy(ObjectId id) {
        return ds.createQuery(DiscountEntity.class)
                .field("creatorId")
                .equal(id)
                .asList();

    }

    public List<DiscountEntity> search(String searchValue, ObjectId userId) {
        final Query<DiscountEntity> query = ds.createQuery(DiscountEntity.class);
        query.and(query.criteria("creatorId").notEqual(userId), 
                query.or(query.criteria("discountName").containsIgnoreCase(searchValue),
                        query.criteria("website").containsIgnoreCase(searchValue)));

        return query.asList();
    }

    public DiscountEntity getById(String discountId) {
        return ds.get(DiscountEntity.class, new ObjectId(discountId));
    }   

    public List<DiscountEntity> getBoughtBy(ObjectId id) {
        return ds.createQuery(DiscountEntity.class)
                .field("buyers")
                .equal(id)
                .asList();
    }
}
