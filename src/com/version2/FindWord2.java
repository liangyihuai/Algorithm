package com.version2;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The difference from the version1 1 is that this class use the JDK method String.indexOf(String str)
 * and String.indexOf(String str, int begin) to find the keyword.
 *
 * Created by liangyh on 5/20/16.
 */
public class FindWord2 {
    /**
     * the index+1 of list is the chapter,
     * and the value is the keywork number which the chapter contains.
     */
    private final List<Integer> nums = new LinkedList<Integer>();

    /**
     * the keyword
     */
    private final static String keyWord = "Arthur";


    public void doCount(String path){
        if(path == null) return;

        File file = new File(path);

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file)));

            int chapter = 1;

            String line = reader.readLine();
            while(line != null){
                if(("Chapter "+chapter).equals(line.trim())){
                    int count = 0;
                    while(line != null && !("Chapter "+(chapter+1)).equals(line.trim())){
                        count += countLine(line);

                        line = reader.readLine();
                    }
                    if(line != null){
                        //new chapter
                        nums.add(count);
                        chapter++;
                    }else{
                        //text end
                        nums.add(count);
                        break;
                    }
                }
                if(!("Chapter "+chapter).equals(line.trim())){
                    line = reader.readLine();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * count the keyword in one line string.
     * @param content
     * @return the count
     */
    private int countLine(String content){
        int count = 0;
        if(content != null){
            int index = content.indexOf(keyWord);
            while(index != -1){
                count++;
                index = content.indexOf(keyWord, index+6);
            }
        }
        return count;
    }


    /**
     * print the num of keywork and the total num.
     */
    public void printInfo(){
        Iterator<Integer> ite = nums.iterator();
        int count = 0;
        int temp = 0;
        int index = 1;
        while(ite.hasNext()){
            temp = ite.next();
            System.out.println("chapter: "+index++ +" --> num: "+ temp);
            count += temp;
        }
        System.out.println();
        System.out.println("total num is :"+count);
    }


}
