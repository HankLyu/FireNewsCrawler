/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firenewssearch;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class WordProducer {

    private final NewsData content;
    private final String wordTemplate;
    private String destWrod;

    public WordProducer(NewsData news) {
        this.content = news;
        this.wordTemplate = System.getProperty("user.dir") + "\\news_template.xml";
    }

    private String setWordTitle() {
        System.err.println("print word" + content.getTitle());
        String[] split = content.getDate().toString().split("T");
        return split[0] + content.getTitle();
    }

    public void printWordFile() {
        File in = new File(wordTemplate);
        File out = new File(setWordTitle() + ".xml");
        HashMap<String, String> map = new HashMap<>();
        String[] timeSplit = content.getDate()
                .toString().split("T");
        map.putIfAbsent("{$Title}", content.getTitle());
        map.putIfAbsent("{$Date}", timeSplit[0]);
        map.putIfAbsent("{$Publisher", content.getPublisher());
        map.putIfAbsent("{$Repoter}", content.getRepoter());
        map.putIfAbsent("{$Content", content.getNewsContent());

        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(in));
            BufferedWriter bw = new BufferedWriter(new FileWriter(out));
            int flushCheckSize = 0;
            while ((line = br.readLine()) != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (line.contains(entry.getKey())) {
                        line = "<w:t>" + entry.getValue() + "</w:t>";
                    }
                }
                flushCheckSize += line.length();
                bw.write(line);
                bw.newLine();
                if (flushCheckSize >= 1 << 10) {
                    bw.flush();
                    flushCheckSize = 0;
                }
            }
            bw.close();
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(WordProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
