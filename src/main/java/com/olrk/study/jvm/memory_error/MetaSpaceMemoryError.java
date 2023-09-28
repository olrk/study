package com.olrk.study.jvm.memory_error;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * @author liaoruikai
 * @date 2023-09-28
 */
public class MetaSpaceMemoryError {
    /**
     * 通过 cglib 不断创建类来模拟元空间内存溢出 OutOfMemoryError: Metaspace <br/>
     * 1、将 -XX:MaxMetaspaceSize 设置小一点比较容易模拟，比如：-XX:MaxMetaspaceSize=10M  <br/>
     * 2、开启 -XX:+HeapDumpOnOutOfMemoryError，发生 OOM 时将自动 dump 堆栈到 .hprof 文件
     */
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MetaSpaceMemoryError.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invoke(o, objects));
            enhancer.create();
        }
    }
}
