/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firenewssearch;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author user
 */
public class LtnSearcher implements NewsCrawlerListInterface {

    SearchListConfig config;
    ArrayList<String> foundUrList;

    public LtnSearcher(SearchListConfig config) {
        foundUrList = new ArrayList<>();
        this.config = config;
    }

    @Override
    public int CrawlerNewsUrl() {
        try {
            Document doc = Jsoup.connect(config.getSearchListUrl()).get();
            doc.getElementsByAttributeValue("class", "tit")
                    .forEach((elem) -> {
                        //System.out.println(elem);
                        foundUrList.add("http://news.ltn.com.tw/" + elem.attr("href"));
                    });
        } catch (IOException e) {
        }

        return foundUrList.size();
    }

    @Override
    public ArrayList<String> getFoundUrl() {
        return (ArrayList< String>) foundUrList.clone();
    }

    @Override
    public NewsData parseNews(String newsUrl) {
        NewsData.Builder b = new NewsData.Builder();
        try {
            Document doc = Jsoup.connect(newsUrl).get();

            String[] split = doc.getElementsByTag("title")
                    .first().text().split("-");
            String wanted = doc.getElementsByAttributeValue("data-desc", "內文")
                    .toString();
            doc = Jsoup.parse(wanted);
            String[] splitContent = doc.getElementsByTag("p")
                    .text().split(" ");
            String dataContent = "", repoter = "";
            for (String s : splitContent) {
                if (s.endsWith("）") == false) {
                    if (s.startsWith("〔")) {
                        String[] publisherSplit = s.split("〕");
                        repoter = publisherSplit[0].substring(1);
                        dataContent += publisherSplit[1];
                    } else {
                        dataContent += s;
                    }
                }
            }
            String[] timeFormat = doc.getElementsByTag("span").text().split(" ");
            String time;
            System.err.println("Parse News" + split[0]);
            if (timeFormat.length == 1) {
                time = timeFormat[0]+"T08:00:00Z";
            } else {
                time = timeFormat[0] + "T" + timeFormat[1] + ":00Z";
            }
            b.setTitle(split[0])
                    .setPublisher(split[2])
                    .setDate(time)
                    .setNewsContent(dataContent)
                    .setRepoter(repoter);
        } catch (IOException e) {
        }
        return b.create();
    }

    @Override
    public ArrayList<NewsData> parseAllNews() {
        ArrayList<NewsData> result = new ArrayList<>();
        foundUrList.forEach((url) -> {
            NewsData news = parseNews(url);
            for(int i=0; i<10; i++){
                if(news.getTitle() == null){
                    System.out.println("reparse: " + url);
                    news = parseNews(url);
                }else{
                    result.add(news);
                    break;
                }
            }
        });
        return result;
    }

}
