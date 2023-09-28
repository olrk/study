package com.olrk.study.jvm.memory_error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liaoruikai
 * @date 2023-09-28
 */
public class HeapMemoryError {
    /**
     * 通过不断创建对象模拟堆内存异常 <br/>
     * 1、将 -Xms -Xmx 设置小一点比较容易模拟，比如：-Xms256m -Xmx256m  <br/>
     * 2、开启 -XX:+HeapDumpOnOutOfMemoryError，发生 OOM 时将自动 dump 堆栈到 .hprof 文件
     */
    public static void main(String[] args) {
//        javaHeapSpace();
        gcOverheadLimitExceeded();
    }

    /**
     * GC overhead limit exceeded
     */
    private static void gcOverheadLimitExceeded() {
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            Map<String, Object> map = new HashMap<>();
            for (int j = 0; j < i; j++) {
                map.put(String.valueOf(j), j);
            }
            mapList.add(map);
        }
    }

    /**
     * OutOfMemoryError: Java heap space
     */
    private static void javaHeapSpace() {
        List<String> list = new ArrayList<>();
        while (true) {
            // JDK8开始，字符串常量池位于堆内存中
            list.add("1");
        }
    }

}