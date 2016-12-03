/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.discount.rs;

import com.rf.projectd.discount.rs.response.DiscountResponse;
import java.util.List;

/**
 *
 * @author raz
 */
class SearchResult {
    private final List<DiscountResponse> data;
    private final long searchCount;

    public SearchResult(List<DiscountResponse> data, long searchCount) {
        this.data = data;
        this.searchCount = searchCount;
    }

    public List<DiscountResponse> getData() {
        return data;
    }

    public long getSearchCount() {
        return searchCount;
    }
    
}
