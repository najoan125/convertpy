package com;

import com.File.FilePath;
import com.python.PyVariable;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        PyVariable pyVariable = new PyVariable();
        FilePath filePath = new FilePath();
        String[] lineArray = filePath.readLinesToStringArray("/home/najoan/문서/test.txt");

        System.out.println(Arrays.toString(lineArray)); //파일을 배열로 변환한 것을 출력
        System.out.println(pyVariable.getVariableType(lineArray[0]));
        System.out.println(pyVariable.getVariableValue(lineArray[0]));
    }
}
