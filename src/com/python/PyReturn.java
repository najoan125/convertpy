package com.python;

public class PyReturn {
    public boolean isReturn(String line){
        PyVariable pyVariable = new PyVariable();
        if (!pyVariable.isVariable(line)){
            return line.startsWith("return");
        }
        return false;
    }

    public String getValue(String line){
        if (isReturn(line)){
            return line.substring(6);
        }
        return null;
    }

    public String returnToString(String line){
        if (isReturn(line)){
            return getValue(line)+"을(를) 반환하며 함수 종료";
        }
        return null;
    }
}
