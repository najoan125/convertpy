package com.File;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FilePath {
    public String[] readLinesToStringArray(String path) throws IOException {
        BufferedReader reader = getBufferedReader(path);
        ArrayList<String> result = new ArrayList<>();

        String readLine;
        while ((readLine = reader.readLine()) != null){
            if (readLine.contains("\t")){
                result.add(getTabString(countTab(readLine))+readLine.replaceAll("\\s",""));
            }
            else {
                result.add(readLine.replaceAll("\\s", ""));
            }
        }

        return result.toArray(new String[0]);
    }
    private int countTab(String line){
        return line.length() - line.replace("\t","").length();
    }
    private String getTabString(int count){
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<count;i++){
            builder.append("\t");
        }
        return builder.toString();
    }
    public BufferedReader getBufferedReader(String path) throws FileNotFoundException {
        return new BufferedReader(
                new FileReader(path)
        );
    }
}
