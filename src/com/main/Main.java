package com.main;

import com.python.FromPython;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        FromPython a = new FromPython();
        String[] test = fileLinesToStringArr("C:/Users/i7PC/Downloads/Untitled-1.sk");
        System.out.println(Arrays.toString(test)); //파일을 배열로 변환한 것을 출력
        System.out.println(a.isVariable(test[0])); //첫 번째 줄의 코드가 변수인지 아닌지 출력
        System.out.println(a.isVariableInteger(test[0])); //첫 번째 줄의 코드가 정수인지 아닌지 출력
    }
    
    private static String[] fileLinesToStringArr(String path) throws IOException {
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

    private static BufferedReader getBufferedReader(String path) throws FileNotFoundException {
        return new BufferedReader(
                new FileReader(path)
        );
    }
}
