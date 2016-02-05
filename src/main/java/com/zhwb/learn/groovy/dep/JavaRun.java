package com.zhwb.learn.groovy.dep;

import com.google.common.io.ByteStreams;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author zhangwenbin
 * @since 2016/2/5.
 */
public class JavaRun {
    public static void main(String[] args) throws Exception {
//        sameLevelLoader(); // while throw class not found

        parentChildLevelLoader();
    }

    private static void parentChildLevelLoader() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        FileInputStream inputStream1 = new FileInputStream("javasource/Utils.class");
        CustomClassLoader loader1 = new CustomClassLoader(Thread.currentThread().getContextClassLoader());
        loader1.add("com.zhwb.learn.groovy.Utils", ByteStreams.toByteArray(inputStream1));
        loader1.loadClass("com.zhwb.learn.groovy.Utils");

        CustomClassLoader loader = new CustomClassLoader(loader1);
        FileInputStream inputStream = new FileInputStream("javasource/Domain.class");
        loader.add("com.zhwb.learn.groovy.Domain", ByteStreams.toByteArray(inputStream));
        Class<?> aClass = loader.loadClass("com.zhwb.learn.groovy.Domain");

        Object o = aClass.newInstance();
        aClass.getDeclaredMethod("doIt").invoke(o);
    }

    private static void sameLevelLoader() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        CustomClassLoader loader = new CustomClassLoader(Thread.currentThread().getContextClassLoader());
        FileInputStream inputStream = new FileInputStream("javasource/Domain.class");
        loader.add("com.zhwb.learn.groovy.Domain", ByteStreams.toByteArray(inputStream));
        Class<?> aClass = loader.loadClass("com.zhwb.learn.groovy.Domain");

        FileInputStream inputStream1 = new FileInputStream("javasource/Utils.class");
        CustomClassLoader loader1 = new CustomClassLoader(Thread.currentThread().getContextClassLoader());
        loader1.add("com.zhwb.learn.groovy.Utils", ByteStreams.toByteArray(inputStream1));
        loader1.loadClass("com.zhwb.learn.groovy.Utils");

        Object o = aClass.newInstance();
        aClass.getDeclaredMethod("doIt").invoke(o);
    }
}
