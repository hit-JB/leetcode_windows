package com.company;

public interface Default {
    int num = Integer.MAX_VALUE;
    static void test1(){

        System.out.println("Interface Default static impl");
    }
    default void test2()
    {
        System.out.println("Interface Impl 2");
    }
}
