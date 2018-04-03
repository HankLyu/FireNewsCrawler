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
public class NewsData {

    private final String title;
    private final String repoter;
    private final Instant newsTime;
    private final String content;
    private final String publisher;
    
    public String getTitle() {
        return title;
    }

    public Instant getDate() {
        return newsTime;
    }

    public String getRepoter() {
        return repoter;
    }

    public String getNewsContent() {
        return content;
    }
    
    public String getPublisher(){
        return publisher;
    }

    public static class Builder {

        private String title;
        private String repoter;
        private Instant newsTime;
        private String content;
        private String publisher;
        private NewsData news;

        public Builder setTitle(String title) {
            this.title = title;
            
            return this;
        }

        public Builder setDate(String date) {
            this.newsTime = Instant.parse(date);
            return this;
        }

        public Builder setRepoter(String repoter) {
            this.repoter = repoter;
            return this;
        }

        public Builder setNewsContent(String newsContent) {
            this.content = newsContent;
            return this;
        }
        
        public Builder setPublisher(String publisher){
            this.publisher = publisher;
            return this;
        }

        public NewsData create() {
            news = new NewsData(this);
            return news;
        }
    }

    private NewsData(Builder b) {
        this.title = b.title;
        this.repoter = b.repoter;
        this.newsTime = b.newsTime;
        this.content = b.content;
        this.publisher = b.publisher;
    }
}
