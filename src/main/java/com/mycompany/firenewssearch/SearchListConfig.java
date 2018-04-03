/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firenewssearch;

import java.time.Instant;
import java.util.Date;

/**
 *
 * @author user
 */
public class SearchListConfig{

    private final String searchListUrl;
    private final Date afterDate;

    public String getSearchListUrl() {
        return searchListUrl;
    }

    public Date getAfterDate() {
        return afterDate;
    }

    public static class Builder {

        private String searchListUrl;
        private Date afterDate = null;
        private SearchListConfig config;

        public Builder setSearchListUrl(String url){
            this.searchListUrl = url;
            return this;
        }
        
        public Builder setAfterDate(Date afterDate){
            this.afterDate = afterDate;
            return this;
        }
        
        public Builder setAfterDate(String afterDate){
            this.afterDate = Date.from(Instant.parse(afterDate));
            return this;
        }
        
        public SearchListConfig build(){
            this.config = new SearchListConfig(this);
            return config;
        }
    }

    private SearchListConfig(Builder b) {
        searchListUrl = b.searchListUrl;
        afterDate = b.afterDate;
    }
}
