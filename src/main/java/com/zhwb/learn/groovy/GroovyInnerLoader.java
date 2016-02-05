package com.zhwb.learn.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class GroovyInnerLoader {
    public static void main(final String[] args) throws IllegalAccessException, InstantiationException, IOException, InterruptedException {
        // Create GroovyClassLoader.
        final GroovyClassLoader classLoader = new GroovyClassLoader();

        // Create a String with Groovy code.
        final StringBuilder groovyScript = new StringBuilder();
        groovyScript.append("class Sample {");
        groovyScript.append("  String sayIt(name) { \"Groovy says: Cool $name!\" }");
        groovyScript.append("}");

        for (int i = 0; i < 99999999; i++) {
            Class groovy = classLoader.parseClass(groovyScript.toString());
            System.out.println(i);
        }
        sleep(5000000);
    }
}