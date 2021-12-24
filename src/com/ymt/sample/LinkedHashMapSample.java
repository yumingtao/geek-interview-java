package com.ymt.sample;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description TODO
 * @author yumingtao
 * @date 2021-12-24 15:39:18
 */
public class LinkedHashMapSample {
    /**
     * 结果如下：
     * Project1:Project1
     * Project2:Project2
     * Project3:Project3
     * Iterate over should be not affected
     * Project1:Project1
     * Project2:Project2
     * Project3:Project3
     * Oldest entry should be removed:
     * Project2:Project2
     * Project3:Project3
     * Project4:Project4
     */
    public static void main(String[] args) {
        LinkedHashMap<String, String> accessOrderMap = new LinkedHashMap<String, String>() {
            /**
             * 当size大于3的时候，删除掉最不常用的
             * @param eldest
             * @return
             */
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 3;
            }
        };

        accessOrderMap.put("Project1", "Project1");
        accessOrderMap.put("Project2", "Project2");
        accessOrderMap.put("Project3", "Project3");
        accessOrderMap.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });

        //模拟访问
        accessOrderMap.get("Project2");
        accessOrderMap.get("Project2");
        accessOrderMap.get("Project3");
        System.out.println("Iterate over should be not affected");
        accessOrderMap.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });

        //出发删除
        accessOrderMap.put("Project4", "Project4");
        System.out.println("Oldest entry should be removed:");
        accessOrderMap.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
    }
}
