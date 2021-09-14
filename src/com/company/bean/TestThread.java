package com.company.bean;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestThread {
    public static void main(String[] args){
        //创建一个线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
        pool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟三秒");
            }
        },3,TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟1秒后，，没三秒执行一次");
            }
        },1,3,TimeUnit.SECONDS);
    }
}
