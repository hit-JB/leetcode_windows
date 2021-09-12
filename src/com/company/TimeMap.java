package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeMap {
    Map<String, TreeMap<Integer,String>> map;
    public static void main(String[] args){
        TimeMap timeMap = new TimeMap();
        timeMap.set("love","high",10);
        timeMap.set("love","low",5);
        System.out.println(timeMap.get("love",10));


    }
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        TreeMap<Integer,String> pairs = map.get(key);
       if(pairs==null){
           pairs = new TreeMap<>();
           pairs.put(timestamp,value);
           map.put(key,pairs);
       }else{
           pairs.put(timestamp,value);
       }
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer,String> pairs = map.get(key);
        if(pairs==null)
            return null;
        else{
            Map.Entry<Integer,String> e = pairs.floorEntry(timestamp);
            return e==null?"":e.getValue();
        }
    }
}

