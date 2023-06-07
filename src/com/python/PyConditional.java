package com.python;

public class PyConditional {
    public boolean isConditional(String line){
        return line.matches("^if.+:$");
    }

    public String getExpression(String line){
        if (isConditional(line)){
            StringBuilder result = new StringBuilder();
            for (int i=2;i<line.length()-1;i++){
                result.append(line.charAt(i));
            }
            return result.toString();
        }
        return null;
    }

    public String conditionalToString(String line){
        if (isConditional(line)){
            return "만약 "+getExpression(line)+"이(가) 참이라면 아래 구문 실행";
        }
        return null;
    }
}
