/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firenewssearch;

import java.time.Instant;
import java.util.Date;
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
public class NewsDataTest {

    public NewsDataTest() {
    }

    static NewsData instance;

    @BeforeClass
    public static void setUpClass() {
        instance = new NewsData.Builder()
                .setDate("2015-04-03T00:00:00Z")
                .setNewsContent("this is content")
                .setRepoter("Hank")
                .setTitle("test title")
                .create();
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
     * Test of getTitle method, of class NewsData.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        String expResult = "test title";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDate method, of class NewsData.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        String expResult = "2015-04-03T00:00:00Z";
        Instant result = instance.getDate();
        assertEquals(expResult, result.toString());
    }

    /**
     * Test of getRepoter method, of class NewsData.
     */
    @Test
    public void testGetRepoter() {
        System.out.println("getRepoter");
        String expResult = "Hank";
        String result = instance.getRepoter();
        System.out.println("result is :" + result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNewsContent method, of class NewsData.
     */
    @Test
    public void testGetNewsContent() {
        System.out.println("getNewsContent");
        String expResult = "this is content";
        String result = instance.getNewsContent();
        assertEquals(expResult, result);
    }

}
