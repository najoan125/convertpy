package com;

import com.File.FilePath;
import com.python.PyConditional;
import com.python.PyFunction;
import com.python.PyReturn;
import com.python.PyVariable;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static PyVariable pyVariable = new PyVariable();
    static PyConditional pyConditional = new PyConditional();
    static PyFunction pyFunction = new PyFunction();
    static PyReturn pyReturn = new PyReturn();
    static FilePath filePath = new FilePath();
    public static void main(String[] args) {
        System.out.print("변환할 파일(.py)의 경로를 입력하세요: ");
        Scanner sc = new Scanner(System.in);
        String filePath = sc.nextLine();

        String[] lineArray = readFile(filePath);
        if (lineArray == null){
            System.out.println(filePath+"을(를) 읽는 데 실패했습니다.");
            System.exit(-1);
        }

        System.out.println();
        printToText(lineArray);
    }

    private static String[] readFile(String path){
        String[] lineArray;
        try {
            lineArray = filePath.readLinesToStringArray(path);
        } catch (IOException e) {
            return null;
        }
        return lineArray;
    }

    private static void printToText(String[] lineArray){
        int i = 0;
        for (String s : lineArray){
            i++;
            if (s.contains("\t")){
                int originalLength = s.length();
                s = s.replace("\t","");
                printTabString(originalLength - s.length());
            }
            if (s.trim().equals("")) continue;

            if (pyVariable.isVariable(s)){
                System.out.println(variable(i, s));
            }
            else if (pyConditional.isConditional(s)){
                System.out.println(pyConditional.conditionalToString(s));
            }
            else if (pyFunction.isFunction(s)){
                System.out.println(pyFunction.functionToString(s));
            }
            else if (pyReturn.isReturn(s)){
                System.out.println(pyReturn.returnToString(s));
            }
            else if (pyFunction.isFunctionCall(s)){
                System.out.println(pyFunction.functionCallToString(s));
            }
            else{
                System.out.println(getErrorMessage(i, s));
            }
        }
    }

    private static void printTabString(int count){
        for (int i=0;i<count;i++){
            System.out.print("\t");
        }
    }

    private static String getErrorMessage(int count, String line){
        return "Line " + count + "> " + line + ": 구문 해석 실패";
    }

    private static String variable(int count, String line){
        if (pyVariable.getVariableType(line) == PyVariable.VAR_ERROR) {
            return getErrorMessage(count,line);
        }
        String toString = pyVariable.variableToString(line);
        pyVariable.registerVariableUsed(line);
        return toString;
    }
}
