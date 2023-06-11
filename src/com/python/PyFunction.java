package com.python;

public class PyFunction {
    public boolean isFunction(String line){
        return line.startsWith("def") && line.endsWith(":");
    }

    public boolean isFunctionCall(String line){
        if (!isFunction(line)){
            return line.contains("(") && line.contains(")");
        }
        return false;
    }

    public String getFunctionCallName(String line){
        if (!isFunctionCall(line)) return null;

        int idx = line.indexOf("(");
        return line.substring(0,idx);
    }

    public String functionCallParameters(String line){
        if (!isFunctionCall(line)) return null;

        int idx = line.indexOf("(");
        return line.substring(idx+1,line.length()-1);
    }

    public String functionCallToString(String line){
        if (!isFunctionCall(line)) return null;

        String parameters = functionCallParameters(line);
        if (parameters.equals("")){
            return "매개변수 없이 " + getFunctionCallName(line) + " 함수 호출";
        }
        return "매개변수 " + parameters + "을(를) 넘기며 " + getFunctionCallName(line) + " 함수 호출";
    }

    public String getName(String line){
        if (!isFunction(line)) return null;

        StringBuilder result = new StringBuilder();
        for (char c : line.substring(3).toCharArray()){
            if (c != '('){
                result.append(c);
            }
            else{
                break;
            }
        }
        return result.toString();
    }

    public String getParameters(String line){
        if (!isFunction(line)) return null;

        int idx = line.indexOf("(");
        return line.substring(idx+1, line.length()-2);
    }

    public String functionToString(String line){
        return "아래 구문을 실행하는 함수 "+getName(line)+"을(를) 선언(매개변수 "+getParameters(line)+"):";
    }
}
