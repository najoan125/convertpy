package com;

import com.File.FilePath;
import com.python.PyVariable;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static PyVariable pyVariable = new PyVariable();
    static FilePath filePath = new FilePath();
    public static void main(String[] args) {
        System.out.print("변환할 파일(.py)의 경로를 입력하세요: ");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        String[] lineArray = new String[0];
        try {
            lineArray = filePath.readLinesToStringArray(userInput);
        } catch (IOException e) {
            System.out.println(userInput+"을(를) 읽는 데 실패했습니다.");
            System.exit(-1);
        }
        System.out.println();

        convertToText(lineArray);
    }

    private static void convertToText(String[] lineArray){
        int i = 0;
        for (String s : lineArray){
            i++;
            if (pyVariable.isVariable(s)){
                if (pyVariable.getVariableType(s) == PyVariable.VAR_ERROR) {
                    System.out.println("Line " + i + "> " + s + ": 구문 해석 실패");
                } else {
                    System.out.println(pyVariable.variableToString(s));
                }
            }
            else{
                System.out.println("Line "+i+"> "+s+": 구문 해석 실패");
            }
        }
    }
}
