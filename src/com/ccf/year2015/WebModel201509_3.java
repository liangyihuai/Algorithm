package com.ccf.year2015;

import java.util.*;

/**
 * 201509-3，模板生成系统
 * Created by liangyh on 4/10/16.
 */
public class WebModel201509_3 {

    public void doWebModel(){
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        Scanner scanner = new Scanner(reader);
        int lineNum = 0;
        int varNum = 0;
        if(scanner.hasNext()){
            lineNum = scanner.nextInt();
        }

        if(scanner.hasNext()){
            varNum = scanner.nextInt();
        }
        if(lineNum == 0 || varNum == 0)
            return ;

        scanner.nextLine();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < lineNum; i++) {
            builder.append(scanner.nextLine());
        }
        Map<String,String>  variableMap = new HashMap<>(varNum);
        for (int i = 0; i < varNum; i++) {
            String val1 = scanner.nextLine();
            String[] strs = val1.split(" ");
            if(strs.length == 1){
                variableMap.put("\\{\\{ "+strs[0].trim()+" \\}\\}", "");
            }else{
                String value = strs[1].trim();
                if(value.startsWith("\"")){
                    value = value.substring(1);
                }
                if(value.endsWith("\"")){
                    value = value.substring(0, value.length()-1);
                }
                variableMap.put("\\{\\{ "+strs[0].trim()+" \\}\\}", value);
            }
        }

        String content = builder.toString();

        Set<String> set = variableMap.keySet();
        Iterator<String> ite = set.iterator();
        while(ite.hasNext()){
            String key = ite.next();
            String value = variableMap.get(key);

            content = content.replaceAll(key, value);
        }

        content = content.replaceAll("\\{\\{ [a-zA-Z_0-9]+ \\}\\}", "");

        System.out.print(content);
    }

    public static void main(String args[]){
        WebModel201509_3 test = new WebModel201509_3();
        test.doWebModel();
    }
}
