/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firenewssearch;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
class testForSearchList implements NewsCrawlerListInterface {

    private final ArrayList<String> fakeUrlList;

    public testForSearchList() {
        fakeUrlList = new ArrayList<>();
    }

    @Override
    public int CrawlerNewsUrl() {
        fakeUrlList.clear();
        fakeUrlList.add("url1");
        fakeUrlList.add("url2");
        fakeUrlList.add("url3");
        return fakeUrlList.size();
    }

    @Override
    public ArrayList<String> getFoundUrl() {
        return (ArrayList<String>) fakeUrlList.clone();
    }

    @Override
    public NewsData parseNews(String newsUrl) {
        return new NewsData.Builder()
                .setNewsContent(newsUrl)
                .setPublisher(newsUrl)
                .setRepoter(newsUrl)
                .setTitle(newsUrl)
                .setDate("2018-03-26T00:00:00Z")
                .create();
    }

    @Override
    public ArrayList<NewsData> parseAllNews() {
        ArrayList<NewsData> result = new ArrayList<>();
        fakeUrlList.forEach((url) -> {
            result.add(parseNews(url));
        });
        return result;
    }

}

public class NewsManagerTest {

    public NewsManagerTest() {
    }

    public static NewsManager instance;

    @BeforeClass
    public static void setUpClass() {
        instance = new NewsManager();
        instance.addNewsCrawlerListInterface(new testForSearchList());
        instance.searchNews();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of searchNews method, of class NewsManager.
     */
    @Test
    public void testSearchNews() {
        System.out.println("searchNews");
        int expResult = 0;
        int result = instance.searchNews();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasNews method, of class NewsManager.
     */
    @Test
    public void testHasNews() {
        System.out.println("hasNews");
        boolean expResult = true;
        boolean result = instance.hasNews();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNews method, of class NewsManager.
     */
    @Test
    public void testGetNews() {
        System.out.println("getNews");
        NewsData result = instance.getNews();
        assertTrue(result.getTitle().contains("url"));
    }

    /**
     * Test of removeTopNews method, of class NewsManager.
     */
    @Test
    public void testRemoveTopNews() {
        System.out.println("removeTopNews");
        int before = instance.numberOfNews();
        instance.removeTopNews();
        int after = instance.numberOfNews();
        assertEquals(before - 1, after);
    }

}
