/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firenewssearch;

/**
 *
 * @author user
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class NewsManager {

    private int loadPage;
    private Date afterDate;
    private final Queue<NewsData> buffer;
    private final ArrayList<NewsCrawlerListInterface> searchList;


    public NewsManager(Date afterDate, int loadPage) {
        this.searchList = new ArrayList<>();
        this.buffer = new ConcurrentLinkedDeque<>();
        this.loadPage = loadPage;
        this.afterDate = afterDate;
    }

    
    public void addNewsCrawlerListInterface(NewsCrawlerListInterface t){
        searchList.add(t);
    }

    public NewsManager() {
        this.searchList = new ArrayList<>();
        this.buffer = new ConcurrentLinkedDeque<>();
        //url = "http://news.ltn.com.tw/news/life/breakingnews/2353113"; 
        //url = "https://news.google.com/news/search/section/q/%E6%B6%88%E9%98%B2/%E6%B6%88%E9%98%B2?hl=zh-tw&gl=TW&ned=zh-tw_tw";  //google找的新聞
        //url = "https://udn.com/search/result/2/%E6%B6%88%E9%98%B2";
        loadPage = 30;
    }

    public int searchNews() {
        searchList.forEach((s) -> {
            s.CrawlerNewsUrl();
            s.parseAllNews().forEach(((t) -> {
                buffer.add(t);
            }));
        });
        if (buffer.isEmpty()) {
            return 1;
        }
        return 0;
    }

    public boolean hasNews() {
        return !buffer.isEmpty();
    }

    public NewsData getNews() {
        return buffer.peek();
    }

    public void removeTopNews() {
        buffer.remove();
    }

    public NewsData getNewsAndRemove() {
        return buffer.remove();
    }

    public int numberOfNews(){
        return buffer.size();
    }
}
