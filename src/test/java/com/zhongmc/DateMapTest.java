package com.zhongmc;

import java.text.SimpleDateFormat;

/**
 * Created by ZMC on 2017/1/26.
 */
public class DateMapTest {
    public static void main(String [] args){
        /*List<String> listdate = new ArrayList<>();

        listdate.add("2017-1-1");
        listdate.add("2017-2-1");
        listdate.add("2017-3-1");
        listdate.add("2017-5-1");
        listdate.add("2017-6-1");
        listdate.add("2017-3-1");

        Map<String,Integer> tep = new HashMap<String, Integer>();
        for (String str: listdate) {
            tep.put(str,1);
        }
        System.out.println(tep);*/
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        java.util.Date date=new java.util.Date();
        String str=sdf.format(date);
        System.out.println(str);
    }
}
