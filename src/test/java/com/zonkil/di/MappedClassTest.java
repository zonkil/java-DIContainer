package com.zonkil.di;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zonkil on 11.06.2016.
 */
public class MappedClassTest {

    @Test
    public void testClassToInterfaceCreation() {
        MappedClass mp = new MappedClass("com.zonkil.di.examples.SampleInterface", "com.zonkil.di.examples.SampleClass");
        assertTrue(mp != null);
    }
     @Test
    public void testInheritnceClassToIntercaceCreation(){
        MappedClass mp = new MappedClass("com.zonkil.di.examples.SampleInterface", "com.zonkil.di.examples.SubClass2");
        assertTrue(mp != null);
    }

    @Test
    public void testMultipleInheritnceClassToIntercaceCreation(){
        MappedClass mp = new MappedClass("com.zonkil.di.examples.SampleInterface", "com.zonkil.di.examples.SubClass2");
        assertTrue(mp != null);
    }

    @Test
    public void testClassToClassCreation(){
        MappedClass mp = new MappedClass("com.zonkil.di.examples.SampleClass", "com.zonkil.di.examples.SubClass1");
        assertTrue(mp != null);
    }

    @Test
    public void testInheritanceClassToClassCreation(){
        MappedClass mp = new MappedClass("com.zonkil.di.examples.SampleClass", "com.zonkil.di.examples.SubClass2");
        assertTrue(mp != null);
    }

    @Test(expected = RuntimeException.class)
    public void testInterfaceToIntercafaceCreation(){
        MappedClass mp = new MappedClass("com.zonkil.di.examples.SampleInterface", "com.zonkil.di.examples.SampleInterface");
    }

    @Test(expected = RuntimeException.class)
    public void testInterfaceToClassCreation(){
        MappedClass mp = new MappedClass("com.zonkil.di.examples.SubClass2", "com.zonkil.di.examples.SampleInterface");
    }

    @Test(expected = RuntimeException.class)
    public void testNotInheritnaceClassToInterfaceCreation(){
        MappedClass mp = new MappedClass("com.zonkil.di.examples.SampleInterface", "com.zonkil.di.NotInheritanceClass");
    }

    @Test(expected = RuntimeException.class)
    public void testNotInheritnaceClassToClassCreation(){
        MappedClass mp = new MappedClass("com.zonkil.di.examples.SampleClass", "com.zonkil.di.NotInheritanceClass");
    }

}