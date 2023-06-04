package com.python;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class PyVariable {
    public static final int VAR_INT = 0;
    public static final int VAR_STR = 1;
    public static final int VAR_LIST = 2;
    public static final int VAR_BOOL = 3;
    public static final int VAR_UNKNOWN = -1;
    public static final int VAR_ERROR = -2;

    private static final HashMap<String,String> variables = new HashMap<>();

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

    /**
     * After run this method, You must run registerVariableUsed().
     * But don't run registerVariableUsed() before run this method.
     */
    public String variableToString(String line){
        switch (getVariableType(line)){
            case VAR_BOOL:
                return getVariableString("Boolean", line);
            case VAR_LIST:
                return getVariableString("List",line);
            case VAR_STR:
                return getVariableString("String", line);
            case VAR_INT:
                return getVariableString("Int", line);
        }
        return null;
    }

    private String getVariableString(String type, String line){
        if (!isVariableUsed(line)) {
            return type + " 타입의 변수 " + getVariableName(line) + "을(를) " + getVariableValue(line) + "으(로) 초기화하여 선언";
        }
        return type+" 타입의 변수 " + getVariableName(line) + "을(를) " + getVariablePreviousValue(line) + "에서 " + getVariableValue(line) + "으(로) 변경";
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
        return variables.containsKey(getVariableName(line));
    }

    public String getVariablePreviousValue(String line){
        return variables.get(getVariableName(line));
    }

    /**
     * Duplicate registration updates the saved variable
     */
    public void registerVariableUsed(String line){
        variables.put(getVariableName(line),getVariableValue(line));
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
