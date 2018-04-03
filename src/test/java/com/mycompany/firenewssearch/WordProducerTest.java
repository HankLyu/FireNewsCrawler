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
public class WordProducerTest {
    
    public WordProducerTest() {
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
     * Test of printWordFile method, of class WordProducer.
     */
    @Test
    public void testPrintWordFile() {
        System.out.println("printWordFile");
        NewsData testNews = new NewsData.Builder()
                .setDate("2015-04-03T00:00:00Z")
                .setNewsContent("this is content")
                .setRepoter("Hank")
                .setPublisher("HankPub")
                .setTitle("ggtitle")
                .create();
        WordProducer instance = new WordProducer(testNews);
        instance.printWordFile();
    }
    
}
