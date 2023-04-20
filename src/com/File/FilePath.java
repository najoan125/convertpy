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
            if (readLine.startsWith("\t")){
                result.add("\t"+readLine.replaceAll("\\s",""));
            }
            else {
                result.add(readLine.replaceAll("\\s", ""));
            }
        }

        return result.toArray(new String[0]);
    }
    public BufferedReader getBufferedReader(String path) throws FileNotFoundException {
        return new BufferedReader(
                new FileReader(path)
        );
    }
}
