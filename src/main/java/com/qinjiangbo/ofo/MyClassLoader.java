package com.qinjiangbo.ofo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @date: 27/03/2017 12:43 PM
 * @author: qinjiangbo@github.io
 */
public class MyClassLoader extends URLClassLoader {

    public MyClassLoader() {
        super(new URL[0]);
    }

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, InterruptedException {
        MyClassLoader classLoader = new MyClassLoader();
        Class clazz = classLoader.loadClass("com.qinjiangbo.ofo.Address");
        System.out.println("1-" + clazz.getSimpleName());

        clazz = Class.forName("com.qinjiangbo.ofo.Address");
        System.out.println("2-" + clazz.getSimpleName());

        clazz = ClassLoader.getSystemClassLoader()
                .loadClass("com.qinjiangbo.ofo.Address");
        System.out.println("3-" + clazz.getSimpleName());

        clazz = Thread.currentThread().getContextClassLoader()
                .loadClass("com.qinjiangbo.ofo.Address");
        System.out.println("4-" + clazz.getSimpleName());

        MyClassLoader classLoader2 = new MyClassLoader();
        Class clazz2 = classLoader2.loadClass("com.qinjiangbo.ofo.Address");
        System.out.println("5-" + clazz2.getSimpleName());

        System.out.println("1* " + clazz.getClassLoader());
        System.out.println("2* " + clazz.getClassLoader());

        File file = new File("/Users/Richard/Downloads");
        URL[] urls = {file.toURL()};
        ClassLoader loader1 = new URLClassLoader(urls);
        ClassLoader loader2 = new URLClassLoader(urls);

        Class clazz3 = loader1.loadClass("Hello");
        Class clazz4 = loader2.loadClass("Hello");
        System.out.println(clazz3 == clazz4);
        System.out.println("3* " + clazz3.getClassLoader());
        System.out.println("4* " + clazz4.getClassLoader());

        System.out.println(Runtime.getRuntime().availableProcessors());

    }
}
