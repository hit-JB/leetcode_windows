package com.company;
import javax.print.DocFlavor;
import java.util.*;

import java.awt.desktop.SystemEventListener;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static final double CM_CER_INCH = 2.54;//this is global variable note the "const" is a keyword of python not used.
    public static void main(String[] args) {
        Main sol =new Main();
        String s =  "373781";
        System.out.println(sol.longestAwesome(s));
    }
    public List<List<String>> displayTable(List<List<String>> orders) {
        List<List<String>> ret= new ArrayList<>();
        List<String> temp = new ArrayList<>();
        Map<String, Integer> foods = new HashMap<>();
        orders.sort(Comparator.comparingInt(e -> Integer.parseInt(e.get(1))));
        Set<String> sortFood = new HashSet<>();
        for(List<String> e:orders) {
           sortFood.add(e.get(2));
        }
        List<String> list = new ArrayList<>(sortFood);

        list.sort(String::compareTo);

        for(String e:list) {
            if (foods.containsKey(e))
                continue;
            foods.put(e, foods.size());
        }
        temp.add("Table");
        temp.addAll(list);
        ret.add(new ArrayList<>(temp));
        int i=0;

        List<String> zeros = new ArrayList<>();
        for(int k=0;k<foods.size();k++)
            zeros.add("0");
        temp.clear();
        while(i<orders.size()){
            if(temp.isEmpty() || orders.get(i).get(1).equals(temp.get(0))) {
                if (temp.isEmpty()) {
                    temp.add(orders.get(i).get(1));
                    temp.addAll(zeros);
                }
                String foodItem = orders.get(i).get(2);
                int index = foods.get(foodItem)+1;
                temp.set(index,String.valueOf(Integer.parseInt(temp.get(index))+1));
                i++;
            }else {
                ret.add(new ArrayList<>(temp));
                temp.clear();
            }
            if(i==orders.size()){
                ret.add(new ArrayList<>(temp));
                temp.clear();
            }
        }
        return ret;
    }
    public List<List<String>> shortDisplay(List<List<String>> orders){
        List<String> head = orders.stream().map(o -> o.get(2)).distinct().sorted()
                .collect(Collectors.toCollection(() -> Stream.of("Table").collect(Collectors.toList())));
        return orders.stream()
                .collect(Collectors.groupingBy(o -> Integer.parseInt(o.get(1)),
                        Collectors.groupingBy(o -> o.get(2), Collectors.counting())))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> head.stream().skip(1).map(food -> entry.getValue().getOrDefault(food, 0L).toString())
                        .collect(Collectors.toCollection(() -> Stream.of(entry.getKey().toString()).collect(Collectors.toList()))))
                .collect(Collectors.toCollection(() -> Stream.of(head).collect(Collectors.toList())));
    }
    public int hIndex(int[] citations) {
        List<Integer> list = new ArrayList();
        for(int e:citations)
            list.add(e);
        list.sort((e1,e2)->-Integer.compare(e1,e2));
        int i=0;
        while(i<list.size()){
            int e = list.get(i);
            if(e>i) {
                i++;
                continue;
            }
            break;
        }
        return i;
    }
    public boolean increasingTriplet(int[] nums) {
        if(nums.length<3)
            return false;
        int low=nums[0],mid=Integer.MAX_VALUE,high=Integer.MAX_VALUE;
        int i=1;
        while(i<nums.length){
            int e = nums[i];
            if(e>low && e<mid)
                mid = e;
            else if(e<low)
                low = e;
            else if(e>mid){
                return true;
            }else if(e<mid){
                if(e>low)
                    mid = e;
                else if(low>e)
                    low =e;
            }
            i++;
        }
        return false;
    }
    public int countPairs(int[] deliciousness) {
        double mod = Math.pow(10,9) + 7;
        int max = Integer.MIN_VALUE;
        for(int e:deliciousness)
            max = Math.max(max,e);
        Map<Integer,Integer> map = new HashMap<>();
        double pairs = 0;
        for(int e:deliciousness){
            for(int num=1;num<=2*max;num<<=1){
                int count = map.getOrDefault(num-e,0);
                pairs = (pairs + count) % mod;
            }
            map.put(e,map.getOrDefault(e,0)+1);
        }
        return (int)pairs;
    }
    public int longestAwesome(String s) {
        int max=Integer.MIN_VALUE;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        int sequence = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            int digit = c-'0';
            sequence = sequence ^ (1<<digit);
            if(map.containsKey(sequence))
                max = Math.max(i-map.get(sequence),max);
            else
                map.put(sequence,i);
            int num = 1;
            for(int j=0;j<10;j++){

                int e = sequence ^ num;
                if(map.containsKey(e)){
                    max = Math.max(i-map.get(e),max);
                }
                num = num<<1;
            }

        }
        return max;
    }
}
