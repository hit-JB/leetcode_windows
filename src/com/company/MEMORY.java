package com.company;

public class MEMORY implements Default{

    @Override
    public void test2()  {
        Default.super.test2();
        System.out.println("THis is impl to interface");
    }
    public static void main(String[] args){
        Default temp = new MEMORY();
        temp.test2();
        Default.test1();
        System.out.println(Default.num);
    }
}
