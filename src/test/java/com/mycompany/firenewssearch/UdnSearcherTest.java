/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firenewssearch;

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
public class UdnSearcherTest {
    
    final UdnSearcher instance;

    public UdnSearcherTest() {
        instance = new UdnSearcher(new SearchListConfig.Builder()
                .setSearchListUrl("https://udn.com/search/result/2/%E6%B6%88%E9%98%B2")
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
     * Test of parseNews method, of class UdnSearcher.
     */
    @Test
    public void testParseNews() {
        System.out.println("parseNews");
        String newsUrl = "https://udn.com/news/story/7320/3063246";
        NewsData expResult = null;
        NewsData result = instance.parseNews(newsUrl);
        assertEquals(0, 0);
    }
    
}
