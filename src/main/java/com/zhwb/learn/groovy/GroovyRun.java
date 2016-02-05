package com.zhwb.learn.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovySystem;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class GroovyRun {
    public static void main(final String[] args) throws IllegalAccessException, InstantiationException, IOException, InterruptedException {
        // Create GroovyClassLoader.
        final GroovyClassLoader classLoader = new GroovyClassLoader();

        // Create a String with Groovy code.
        final StringBuilder groovyScript = new StringBuilder();
        groovyScript.append("class Sample {");
        groovyScript.append("  String sayIt(name) { \"Groovy says: Cool $name!\" }");
        groovyScript.append("}");

        // Load string as Groovy script class.
        // loading from same text, makes two different class
        Class groovy = classLoader.parseClass(groovyScript.toString());
        Class groovy2 = classLoader.parseClass(groovyScript.toString());
        GroovyObject groovyObj = (GroovyObject) groovy.newInstance();
        Object output = groovyObj.invokeMethod("sayIt", new Object[]{"mrhaki"});
        assert "Groovy says: Cool mrhaki!".equals(output);
        assert groovy != groovy2;


        // Load Groovy script file.
        // loading from same file, two class is same
        groovy = classLoader.parseClass(new File("src/main/resources/SampleScript.groovy"));
        groovy2 = classLoader.parseClass(new File("src/main/resources/SampleScript.groovy"));
        groovyObj = (GroovyObject) groovy.newInstance();
        output = groovyObj.invokeMethod("scriptSays", new Object[]{"mrhaki", new Integer(2)});
        assert "Hello mrhaki, from Groovy. Hello mrhaki, from Groovy".equals(output);
        assert groovy == groovy2;

        System.gc();
        sleep(5000000);
    }
}