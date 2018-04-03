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
public class LtnSearcherTest {

    private LtnSearcher instance;

    public LtnSearcherTest() {
        instance = new LtnSearcher(new SearchListConfig.Builder()
                .setSearchListUrl("http://news.ltn.com.tw/topic/%E6%B6%88%E9%98%B2%E5%93%A1")
                .build());
        instance.CrawlerNewsUrl();
    }

    @BeforeClass
    public static void setUpClass() {
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
     * Test of parseNews method, of class LtnSearchList.
     */
    @Test
    public void testParseNews() {
        System.out.println("parseNews");
        //String newsUrl = "http://news.ltn.com.tw/news/society/breakingnews/2359318";
        String newsUrl = "http://news.ltn.com.tw/news/society/breakingnews/2367854";
        NewsData result = instance.parseNews(newsUrl);
        String expectTitle = "球友倒地失去心跳 消防局資深救護教官即刻救命 ";
        String expectRepoter = "記者黃美珠／竹北報導";
        assertEquals(expectTitle, result.getTitle());
        assertEquals(expectRepoter, result.getRepoter());
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of parseAllNews method, of class LtnSearchList.
     */
    @Test
    public void testParseAllNews() {
        System.out.println("parseAllNews");
        ArrayList<NewsData> expResult = null;
        ArrayList<NewsData> result = instance.parseAllNews();
        System.out.println("parse all news size: " + result.size());
        assertNotEquals(0, result.size());
    }

    /**
     * Test of getFoundUrl method, of class LtnSearchList.
     */
    @Test
    public void testGetFoundUrl() {
        System.out.println("getFoundUrl");
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getFoundUrl();
        result.forEach((s) -> {
            System.out.println(s);
        });
        assertNotEquals(0, result.size());
    }

}
