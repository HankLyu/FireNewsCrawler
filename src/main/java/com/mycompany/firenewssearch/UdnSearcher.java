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
public class UdnSearcher implements NewsCrawlerListInterface {

    SearchListConfig config;
    ArrayList<String> foundUrList;

    public UdnSearcher(SearchListConfig config) {
        this.config = config;
        this.foundUrList = new ArrayList<>();
    }

    @Override
    public int CrawlerNewsUrl() {
        try {
            Document doc = Jsoup.connect(config.getSearchListUrl()).get();
            doc = Jsoup.parse(doc
                    .getElementsByAttributeValue("id", "search_content")
                    .toString());
            doc.getElementsByAttribute("href").forEach((elem) -> {
                foundUrList.add(elem.attr("href"));
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
            String[] info = doc.getElementsByAttributeValue(
                    "class", "story_bady_info_author")
                    .text().split(" ");
            if (info[1].charAt(0) < '9' && info[1].charAt(0) > '0') {
                String time = info[0] + "T" + info[1].substring(0, 5) + ":00Z";
                b.setDate(info[0] + "T" + info[1].substring(0, 5) + ":00Z");
            } else {
                b.setDate(info[0] + "T08:00:00Z");
            }
            String title = doc.getElementsByTag("Title").text().split("\\|")[0];
            b.setPublisher("聯合報");
            b.setRepoter(info[2]);
            System.err.println("Parse News: " + title + " " + newsUrl);
            b.setTitle(title);
            b.setNewsContent(doc.getElementsByTag("p").text());
        } catch (IOException e) {
        }
        return b.create();
    }

    @Override
    public ArrayList<NewsData> parseAllNews() {
        ArrayList<NewsData> result = new ArrayList<>();
        foundUrList.forEach((url) -> {
            NewsData news = parseNews(url);
            for (int i = 0; i < 10; i++) {
                if (news.getTitle() == null) {
                    System.out.println("reparse: " + url);
                    news = parseNews(url);
                } else {
                    result.add(news);
                    break;
                }
            }
        });
        return result;
    }

}
