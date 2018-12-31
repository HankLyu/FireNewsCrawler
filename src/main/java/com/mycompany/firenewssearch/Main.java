/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firenewssearch;

public class Main {

    public static void main(String[] args) {
        NewsManager manager = new NewsManager();
        String ltnUrl = "http://news.ltn.com.tw/topic/%E6%B6%88%E9%98%B2%E5%93%A1";
        String udnUrl = "https://udn.com/search/result/2/%E6%B6%88%E9%98%B2";

        /*manager.addNewsCrawlerListInterface(
                new LtnSearcher(new SearchListConfig.Builder()
                        .setSearchListUrl(ltnUrl)
                        .build()));
         */
        manager.addNewsCrawlerListInterface(
                new UdnSearcher(new SearchListConfig.Builder()
                        .setSearchListUrl(udnUrl)
                        .build()));

        manager.searchNews();

        while (manager.hasNews()) {
            NewsData news = manager.getNews();
            System.out.println(news.getTitle());
            System.out.println(news.getDate() + " " + news.getRepoter() + " " + news.getPublisher());
            System.out.println(news.getNewsContent());
            System.out.println();
            //WordProducer producer = new WordProducer(news);
            //producer.printWordFile();
            manager.removeTopNews();
        }
    }
}
