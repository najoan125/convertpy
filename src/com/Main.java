package com;

import com.File.FilePath;
import com.python.FromPython;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        FromPython fromPython = new FromPython();
        FilePath filePath = new FilePath();
        String[] lineArray = filePath.readLinesToStringArray("C:/Users/i7PC/Downloads/Untitled-1.sk");

        System.out.println(Arrays.toString(lineArray)); //파일을 배열로 변환한 것을 출력
        System.out.println(fromPython.isVariable(lineArray[0])); //첫 번째 줄의 코드가 변수인지 아닌지 출력
        System.out.println(fromPython.isVariableInteger(lineArray[0])); //첫 번째 줄의 코드가 정수인지 아닌지 출력
    }
}
