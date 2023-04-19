package com.python;

public class FromPython {
    public boolean isVariable(String line){
        return line.matches("\\w+=\\w+");
    }

    public boolean isVariableInteger(String line){
        if (isVariable(line)) {
            String[] value = line.split("=");
            return value[1].matches("^[0-9]*$");
        }
        return false;
    }

    public boolean isVariableString(String line){
        if (isVariable(line)){
            String[] value = line.split("=");
            return value[1].contains("\"");
        }
        return false;
    }

    public boolean isVariableList(String line){
        if (isVariable(line)){
            String[] value = line.split("=");
            return value[1].contains("[");
        }
        return false;
    }
}
