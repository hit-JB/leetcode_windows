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
        int[] nums = new int[]{4,4,4,4};
        System.out.println(sol.canPartitionKSubsets(nums,4));
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
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int[][] pos = new int[][]{{ax1,ay1},{ax2,ay1},{ax2,ay2},{ax1,ay2}};
        int[][] pos1 = new int[][]{{bx1,by1},{bx2,by1},{bx2,by2},{bx1,by2}};
        if(bx1<ax1)
            return computeArea(bx1,by1,bx2,by2,ax1,ay1,ax2,ay2);
        int sum_area = RectangleArea(ax1,ay1,ax2,ay2) +
                RectangleArea(bx1,by1,bx2,by2);
        if(ax1>=bx2 ||bx1 >= ax2 || ay1>by2|| by1>ay2)
            return sum_area;
        else {
            int width = Math.min(ax2,bx2) - Math.max(ax1,bx1);
            int height =Math.min(ay2,by2) - Math.max(ay1,by1);
            return sum_area - width*height;
        }
    }
    public int RectangleArea(int x1,int y1,int x2,int y2){
        return Math.abs(y2-y1) * Math.abs(x2-x1);
    }
    public int removePalindromeSub(String s) {
        return s.length() == 0 ? 0 : new StringBuilder(s).reverse().toString().equals(s) ? 1 : 2;
    }
    public List<List<Integer>> getSkyline(int[][] buildings){
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> b[1] - a[1]);
        List<Integer> boundaries = new ArrayList<Integer>();
        for (int[] building : buildings) {
            boundaries.add(building[0]);
            boundaries.add(building[1]);
        }
        Collections.sort(boundaries);

        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        int n = buildings.length, idx = 0;
        for (int boundary : boundaries) {
            while (idx < n && buildings[idx][0] <= boundary) {
                pq.offer(new int[]{buildings[idx][1], buildings[idx][2]});
                idx++;
            }
            while (!pq.isEmpty() && pq.peek()[0] <= boundary) {
                pq.poll();
            }
            int maxn = pq.isEmpty() ? 0 : pq.peek()[1];
            if (ret.size() == 0 || maxn != ret.get(ret.size() - 1).get(1)) {
                ret.add(Arrays.asList(boundary, maxn));
            }
        }
        return ret;
    }
    public int minPartitions(String n) {
        int count = 0;
        for(char c:n.toCharArray()){
            int e = c-'0';
            if(e>count)
                count +=e-count;
        }
        return count;
    }
    public String intToRoman(int num) {
//        Map<Integer,String> map = new HashMap<>();
//        map.put(5,"V");map.put(10,"X");map.put(50,"L");map.put(100,"C");
//        map.put(500,"D");map.put(1000,"M");map.put(1,"I");
        if(num >=1000)
        {
            int n = num / 1000;
            return "M".repeat(n) +
                    intToRoman(num - n * 1000);
        }else if(num >=900)
                return "CM"+intToRoman(num-900);
        else if(num>=500)
                return "D"+intToRoman(num-500);
        else if(num>=400)
                return "CD"+intToRoman(num-400);
        else if(num >=100) {
            int n = num /100;
            return "C".repeat(n) + intToRoman(num - n * 100);
        }else if( num>=90)
            return "XC"+intToRoman(num-90);
        else if(num >=50)
            return "L"+intToRoman(num-50);
        else if(num >=40)
            return "XL"+intToRoman(num-40);
        else if(num >=10)
        {
            int n = num /10;
            return "X".repeat(n)+intToRoman(num-n * 10);
        }else if (num==9)
            return "IX";
        else if(num >=5)
            return "V"+intToRoman(num-5);
        else if(num==4)
            return "IV";
        else if(num>=1)
            return "I".repeat(num);
        else return "";


        /*
        I             1
V             5
X             10
L             50
C             100
D             500
M             1000
         */

    }
    public boolean verifyPostorder(int[] postorder) {
        return dfsPostorder(postorder,0,postorder.length-1);
    }
    public boolean dfsPostorder(int[] postorder,int i,int j){
        if(j-i<=1)
            return true;
        int min_right  = Integer.MAX_VALUE,max_left = Integer.MIN_VALUE;
        int k = j-1;
       while(k>=i && postorder[k]>postorder[j]){
            min_right = Math.min(postorder[k],min_right);
            k--;
        }
       int div = k;
       while(k>=i && postorder[k]<postorder[j]){
            max_left = Math.max(postorder[k],max_left);
            k--;
       }
       if(max_left > min_right || k!=i-1)
           return false;
       boolean is_left = dfsPostorder(postorder,i,div);
       if(!is_left)
           return false;
        return dfsPostorder(postorder,div+1,j-1);
    }
    public String originalDigits(String s) {
        String[] nums = new String[]{"zero","one","two","three","four","five","six","seven","eight","nine"};
        String[] order=  new String[]{"zero","two","six","eight","seven","five","four","one","three","nine"};
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++)
            map.put(nums[i], i);
        char[] label = new char[]{'z','w','x','g','s','v','f','o','t','e'};
        int[] count = new int[nums.length];
        int[] cs  = new int['z'-'a' +1];
        for(char c:s.toCharArray())
            cs[c-'a']++;
        for(int i=0;i<nums.length;i++){
            String str = order[i];
            char lb   = label[i];
            int num = map.get(str);
            count[num] += cs[lb-'a'];
            if(count[num] == 0)
                continue;
            for(char c:str.toCharArray()){
                cs[c-'a'] -= count[num];
            }
        }
        StringBuilder builder = new StringBuilder();
        for(int num=0;num<count.length;num++){
            builder.append(String.valueOf(num).repeat(count[num]));
        }
        return builder.toString();
    }
    public int findIntegers(int n) {
        String s = Integer.toBinaryString(n);
        int[][] dp = new int[2][s.length()];
        dp[0][0] = 1;
        dp[1][0] = 1;
        for(int i=1;i<s.length();i++){
            dp[0][i] =dp[0][i-1] +dp[1][i-1];
            dp[1][i] = dp[0][i-1];
        }
        int length = s.length();
        if(length==1)
            return 2;
        if(length==2)
            return 3;
        int sum = dp[0][length-1] +1;
        for(int i=1;i<length;i++){
            if(s.charAt(i)=='0')
                continue;
            sum += dp[0][length-i-1]-1;
            if(s.charAt(i-1)=='0')
                sum +=1;
            else {
                break;
            }
        }
        return sum;
    }
    public int flipChess(String[] chessboard) {
        int[][] directs = new int[][]{{-1,0},{-1,1},{0,1},{1,1},
                {1,0},{1,-1},{0,-1},{-1,-1}};
        return  0;
    }
    public boolean checkValidString(String s) {
        int left_high =0,left_low = 0;
        for(char c:s.toCharArray()){
            if(c=='('){
                left_high++;
                left_low++;
            }else if(c==')'){
                left_high--;
                if(left_low>0)
                    left_low--;
            }else{
                left_high++;
                if(left_low>0)
                    left_low--;
            }
            if(left_high<0)
                return false;
        }
        return left_low==0;
    }
    public int minSteps(int n) {
        int[][] dp = new int[n+1][n+1];

        for(int[] e:dp){
            Arrays.fill(e,Integer.MAX_VALUE);
        }
        dp[1][1] = 0;
        for(int i=1;i<=n;i++){
            for(int j=i+1;j<=n;j++){
                if(j-i==i){
                    int min = Integer.MAX_VALUE;
                    for(int k=1;k<=i;k++){
                        min = Math.min(dp[k][i],min);
                    }
                    if(min ==Integer.MAX_VALUE)
                        dp[i][j] = min;
                    else{
                        dp[i][j] = min+2;
                    }
                }else{
                    if(j-i<i)
                        dp[i][j] = Integer.MAX_VALUE;
                    else
                        dp[i][j] = dp[i][j-i]==Integer.MAX_VALUE?Integer.MAX_VALUE:
                                dp[i][j-i]+ 1;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i=1;i<=n;i++){
            min = Math.min(min,dp[i][n]);
        }
        return min;
    }
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int e:nums)sum+=e;
        if(sum % k !=0)
            return false;
        int sub_sum = sum / k;
        Set<Integer> set = new HashSet<>();
        dfsFirst(nums,0,sub_sum,set,new ArrayList<>(),0);
        Map<Integer,Boolean> map = new HashMap<>();
        map.put(0,true);
        dfsCanPartition(map,(1<<nums.length)-1,set);
        return map.get((1<<nums.length)-1);
    }
    public void dfsFirst(int[] nums,int next,int sub_sum,Set<Integer> set,List<Integer> index,int sum){
        if(sum==sub_sum){
            int t = 0;
            for(int id:index){
                t += 1<<id;
            }
            set.add(t);
        }
        if(next==nums.length)
            return;
        for(int i=next;i<nums.length;i++){
            if(sum+nums[i]<=sub_sum){
                index.add(i);
                dfsFirst(nums,i+1,sub_sum,set,index,sum+nums[i]);
                index.remove(index.size()-1);
            }
        }
    }
    public void dfsCanPartition(Map<Integer,Boolean> map,int code,Set<Integer> set){
        if(map.containsKey(code))
            return;
        for(int e:set){
            if((code & e) == e){
                int _code = code & (~e);
                dfsCanPartition(map,_code,set);
                if(map.get(_code)==true){
                    map.put(code,true);
                    return;
                }
            }
        }
        map.put(code,false);
    }
}
