package com.ymt.sample;

import java.util.Set;

/**
 * @Description 验证打印hello world JVM创建了多少个线程
 * @author yumingtao
 * @date 2021-12-31 20:37
 */
public class HelloWorldThreadSample {
    public static void main(String[] args) {
        System.out.println("Hello World!!");
        int activeCount = Thread.activeCount();
        System.out.println("Active thread count is :" + activeCount);
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for(Thread t : threadSet){
            System.out.println("Thread : " + t.getId() + ", name : " + t.getName());
        }
    }
}
