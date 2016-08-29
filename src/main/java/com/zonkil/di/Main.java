package com.zonkil.di;

import com.zonkil.di.annotations.Inject;
import com.zonkil.di.examples.SampleClass;
import com.zonkil.di.examples.SimpleClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by zonkil on 19.05.2016.
 */
public class Main {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        //listClasspath();
        //testMyContainer();
        testAnnotaded();
        //testAnnotatedField();
        //testInheritance();
        //testInterfaces();
    }

//    public static void testMyContainer() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
//        ClassLoader cl = ClassLoader.getSystemClassLoader();
//
//        URL[] urls = ((URLClassLoader) cl).getURLs();
//
//        Container myContainer = new Container();
//        SampleClass sc = myContainer.getBean("com.zonkil.di.examples.SampleClass", SampleClass.class);
//        sc.saySomething();
//    }

    public static void listClasspath() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader) cl).getURLs();

        for (URL url : urls) {
            System.out.println(url.getFile());
        }
    }

    public static void testAnnotaded() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Class<?> clazz = cl.loadClass("com.zonkil.di.examples.SampleClass");


        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f.getName());
            Inject ann = f.getAnnotation(Inject.class);
            System.out.println(ann);

        }
    }

    public static void testAnnotatedFields() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader) cl).getURLs();
        try {
            cl = new URLClassLoader(urls);
            Class<?> clazz = cl.loadClass("com.zonkil.di.examples.SampleClass");

            Annotation[] a = clazz.getAnnotations();
            //printAnnotations(a);
            Constructor<?>[] kon = clazz.getConstructors();

            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                a = f.getAnnotations();
                System.out.println(f.getAnnotatedType().getType().getTypeName());
                //printAnnotations(a);
            }

            Object o = clazz.newInstance();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void testInheritance() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader) cl).getURLs();

        cl = new URLClassLoader(urls);
        Class<?> clazz = cl.loadClass("com.zonkil.di.examples.SubClass2");

        System.out.println("is interface: " + clazz.isInterface());


        Class sc = clazz.getSuperclass();
        while (sc != null) {
            printClassNames(sc);

            sc = sc.getSuperclass();
        }

        //jak widać z przykłądu getSuperclass nie zwraca interfajsu
    }

    //z przykładu widać żę jak klasa bezpośrednio nie implementuje interfajsu to nie jest uznawane że taki interfejs implementuje
    public static void testInterfaces() throws ClassNotFoundException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader) cl).getURLs();

        cl = new URLClassLoader(urls);
        Class<?> clazz = cl.loadClass("com.zonkil.di.examples.SubClass2");

        System.out.println("SubClass2");
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class s : interfaces) {
            printClassNames(s);
        }

        System.out.println("SampleClass");
        clazz = cl.loadClass("com.zonkil.di.examples.SampleClass");

        interfaces = clazz.getInterfaces();
        for (Class s : interfaces) {
            printClassNames(s);
        }

        System.out.println("SampleInterface");
        clazz = cl.loadClass("com.zonkil.di.examples.SampleInterface");

        interfaces = clazz.getInterfaces();
        for (Class s : interfaces) {
            printClassNames(s);
        }

    }

    public static void testConstructors() throws ClassNotFoundException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Class<?> clazz = cl.loadClass(SimpleClass.class.getTypeName());
        Constructor<?>[] constructors = clazz.getConstructors();
        for(Constructor c: constructors){

        }
    }

    public static void printAnnotations(Annotation[] ann) {
        for (Annotation a : ann) {
            System.out.println(a.annotationType());
        }
    }

    public static void printClassNames(Class classes) {
        System.out.println(classes.getName());
        System.out.println(classes.getCanonicalName());
        System.out.println(classes.getSimpleName());
        System.out.println(classes.getTypeName());
        System.out.println("is interface: " + classes.isInterface());
    }


}
