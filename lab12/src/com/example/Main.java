package com.example;

import javax.tools.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class Main {

    public static List getClassesFromJar(String jarName) {

        List classes = new ArrayList();
        System.out.println("Jar: " + jarName);
        try {

            JarInputStream jarFile = new JarInputStream(new FileInputStream(
                    jarName));
            JarEntry jarEntry;

            while (true) {
                jarEntry = jarFile.getNextJarEntry();

                if (jarEntry == null) {
                    break;
                }

                if (jarEntry.getName().endsWith(".class")) {
                    System.out.println("Classes from jar: "
                            + jarEntry.getName());
                    classes.add(jarEntry.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }
    // Java compiler
    private static final class MyDiagnosticListener implements DiagnosticListener {
        @Override
        public void report(Diagnostic diagnostic) {
            System.out.println(diagnostic);
        }
    }

    public static void  main(String[] args) throws MalformedURLException, ClassNotFoundException {

        //get URL to project/jar
        String projectPath = "/home/felix/Desktop/jv/lab12";
        //get name for class
        String className = "TestsClass";
        int passed = 0, failed = 0;


        MyClassloader myLoader = new MyClassloader();
        File path = new File(projectPath);
        if (path.exists()) {
            URL url = path.toURI().toURL();
            //add URL to CLASSPATH
            myLoader.addURL(url);
        }
//package
        Class newClass = myLoader.loadClass(className);
        System.out.println(newClass.getPackage());

        //Get as many information about the class
        if (newClass.isInterface()) {
            System.out.println("This is an interface");
        } else {
            System.out.println("This is NOT an interface");
        }

        System.out.println("\nFields:");
        for (Field field : newClass.getDeclaredFields()) {
            System.out.println(field);
        }

        System.out.println("\nConstructors:");
        for (Constructor constructor : newClass.getConstructors()) {
            System.out.println(constructor);
        }

        System.out.println("\nMethods:");
        for (Method method : newClass.getMethods()) {
            System.out.println(method);
        }
//@test invocation
        System.out.println("\nExecuted Tests:");
        for (Method m : newClass.getMethods()) {
            if (m.isAnnotationPresent(com.example.Test.class)) {
                try {
                    m.invoke(null);
                    passed++;
                } catch (Throwable ex) {
                    System.out.printf("Test %s failed: %s %n",
                            m, ex.getCause());
                    failed++;
                }
            }
        }

        System.out.printf("Passed: %d, Failed %d%n", passed, failed);

        System.out.println("Extracting from jar classes");
        List classes = getClassesFromJar("/home/felix/Desktop/jv/lab12/TestsClassJar.jar");
        System.out.println(classes);

        // Java Compiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        MyDiagnosticListener listener = new MyDiagnosticListener();
        StandardJavaFileManager fileManager =
                compiler.getStandardFileManager(listener, null, null);

        File file = new File("/home/felix/Desktop/jv/src/TestsClass.java");
        Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task =
                compiler.getTask(null, fileManager, listener, null, null, javaFileObjects);
        if (task.call()) {
            System.out.println("Compilation done");
        }
        try {
            fileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        passed = 0; failed = 0;
        String newProjectURL = "C:\\Users\\pinza\\Desktop\\pa\\Lab12\\src";
        String newClassName = "TestsClass";

        File newPath = new File(newProjectURL);
        if (newPath.exists()) {
            URL url = path.toURI().toURL();
            //add URL to CLASSPATH
            myLoader.addURL(url);
        }

        Class newClass1 = myLoader.loadClass(newClassName);
        System.out.println(newClass1.getPackage());
        Object i = null;
        try {
            i = newClass1.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (newClass1.isInterface()) {
            System.out.println("This is an interface");
        } else {
            System.out.println("This is NOT an interface");
        }

        System.out.println("\nFields:");
        for (Field field : newClass1.getDeclaredFields()) {
            System.out.println(field);
        }

        System.out.println("\nMethods:");
        for (Method method : newClass1.getMethods()) {
            System.out.println(method);
        }

        System.out.println("\nConstructors:");
        for (Constructor constructor : newClass1.getConstructors()) {
            System.out.println(constructor);
        }

        System.out.println("\nExecuting Tests:");
        for (Method method : newClass1.getMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    if( Modifier.isStatic(method.getModifiers()))
                    {
                        method.invoke(null);
                        passed++;
                    } else
                    {
                        method.invoke(i);
                        passed++;
                    }
                } catch (Throwable ex) {
                    System.out.printf("Test %s failed: %s %n",
                            method, ex.getCause());
                    failed++;
                }
            }
        }
        System.out.println("Passed: "+passed+"  Failed: "+failed);
    }
}

