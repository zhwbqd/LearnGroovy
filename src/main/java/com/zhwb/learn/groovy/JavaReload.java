package com.zhwb.learn.groovy;

import com.google.common.io.ByteStreams;
import com.zhwb.learn.groovy.dep.CustomClassLoader;

import java.io.FileInputStream;

/**
 * @author zhangwenbin
 * @since 2016/2/5.
 */
public class JavaReload {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream1 = new FileInputStream("javasource/Utils.class");
        CustomClassLoader loader1 = new CustomClassLoader(Thread.currentThread().getContextClassLoader());
        loader1.add("com.zhwb.learn.groovy.Utils", ByteStreams.toByteArray(inputStream1));
        Class<?> aClass = loader1.loadClass("com.zhwb.learn.groovy.Utils");
        System.out.println(aClass.getDeclaredMethod("isOk", new Class[]{String.class}).invoke(aClass, new Object[]{"fuck"}));

        FileInputStream inputStream = new FileInputStream("javasource/v1/Utils.class");
//        loader1 = new CustomClassLoader(Thread.currentThread().getContextClassLoader());
        loader1.add("com.zhwb.learn.groovy.Utils", ByteStreams.toByteArray(inputStream));
        aClass = loader1.loadClass("com.zhwb.learn.groovy.Utils");
        System.out.println(aClass.getDeclaredMethod("isOk", new Class[]{String.class}).invoke(aClass, new Object[]{"fuck"}));

    }

}
