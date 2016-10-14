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

    public List<DiscountEntity> getAllForUser(ObjectId id) {
        return ds.createQuery(DiscountEntity.class)
                .field("userId")
                .equal(id)
                .asList();
                
    }
}
