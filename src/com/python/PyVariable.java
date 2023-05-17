package com.python;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PyVariable {
    public static final int VAR_INT = 0;
    public static final int VAR_STR = 1;
    public static final int VAR_LIST = 2;
    public static final int VAR_BOOL = 3;
    public static final int VAR_UNKNOWN = -1;
    public static final int VAR_ERROR = -2;

    private static ArrayList<String> variableNames = new ArrayList<>();

    public boolean isVariable(String line){
        Queue<String> queue = new LinkedList<>();
        for (int i=0;i<line.length();i++){
            if (line.charAt(i) == '='){
                queue.add("=");
            }
        }
        if (queue.size() == 1)
            return line.matches("\\w+=.+");
        return false;
    }

    public int getVariableType(String line){
        if (isVariable(line)){
            if (isVariableBoolean(line)){
                return VAR_BOOL;
            }
            else if (isVariableList(line)){
                return VAR_LIST;
            }
            else if (isVariableString(line)){
                return VAR_STR;
            }
            else if (isVariableInteger(line)){
                return VAR_INT;
            }
            else return VAR_ERROR;
        }
        return VAR_UNKNOWN;
    }

    public boolean isVariableUsed(String line){
        return variableNames.contains(getVariableName(line));
    }

    public void registerVariableUsed(String line){
        variableNames.add(getVariableName(line));
    }

    public String getVariableValue(String line){
        if (isVariable(line)){
            return line.split("=")[1];
        }
        return null;
    }

    public String getVariableName(String line){
        if (isVariable(line)){
            return line.split("=")[0];
        }
        return null;
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

    public boolean isVariableBoolean(String line){
        if (isVariable(line)){
            String[] value = line.split("=");
            return value[1].contains("False") || value[1].contains("True");
        }
        return false;
    }
}
