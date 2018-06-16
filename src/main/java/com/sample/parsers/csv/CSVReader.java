package com.sample.parsers.csv;


import com.sample.core.core.driver.WebDriverManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private static final char COMA_SEPERATOR = ' ';
    String USERS_CSV_PATH = "sample_urls.csv";

    List<String> list = new ArrayList<>();

    public List<String> parseUsers() throws IOException {
        com.opencsv.CSVReader reader = null;
        try {
            reader = new com.opencsv.CSVReader(new FileReader(USERS_CSV_PATH), COMA_SEPERATOR);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> list = new ArrayList<>();
        String[] record = null;
        while ((record = reader.readNext()) != null) {
            list.add(record[0]);
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        List<String> list = new CSVReader().parseUsers();

        for (int i = 0; i < 3; i++) {
            WebDriverManager.getUrl(list.get(i));

            FileWriter fWriter = null;
            BufferedWriter writer = null;
            try {
                fWriter = new FileWriter("output/"+list.get(i)+".html");
                writer = new BufferedWriter(fWriter);
                String sourceHtml = WebDriverManager.getDriver().getPageSource();
                writer.write(sourceHtml);
                writer.newLine(); //this is not actually needed for html files - can make your code more readable though
                writer.close(); //make sure you close the writer object
            } catch (Exception e) {
                //catch any exceptions here
            }
        }
    }
}
