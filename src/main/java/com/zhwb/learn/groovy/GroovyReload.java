package com.zhwb.learn.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.IOException;

public class GroovyReload {
    public static void main(final String[] args) throws IllegalAccessException, InstantiationException, IOException {
        // Create GroovyClassLoader.
        final GroovyClassLoader classLoader = new GroovyClassLoader();

        // Create a String with Groovy code.
        StringBuilder groovyScript = new StringBuilder();
        groovyScript.append("class Sample {");
        groovyScript.append("  String sayIt(name) { \"Groovy says: Cool $name!\" }");
        groovyScript.append("}");

        Class groovy = classLoader.parseClass(groovyScript.toString());
        GroovyObject groovyObj = (GroovyObject) groovy.newInstance();
        Object output = groovyObj.invokeMethod("sayIt", new Object[]{"jack"});
        System.out.println(output);

        //test reload
        // Create a String with Groovy code.
        groovyScript = new StringBuilder();
        groovyScript.append("class Sample {");
        groovyScript.append("  String sayIt(name) { \"test Groovy says: Cool $name!\" }");
        groovyScript.append("}");

        groovy = classLoader.parseClass(groovyScript.toString());
        groovyObj = (GroovyObject) groovy.newInstance();
        output = groovyObj.invokeMethod("sayIt", new Object[]{"jack"});
        System.out.println(output);
    }
}