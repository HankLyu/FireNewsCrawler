/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firenewssearch;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author user
 */
public interface NewsCrawlerListInterface {
    public int CrawlerNewsUrl();
    public ArrayList<String> getFoundUrl();
    public NewsData parseNews(String newsUrl);
    public ArrayList<NewsData> parseAllNews();
}
