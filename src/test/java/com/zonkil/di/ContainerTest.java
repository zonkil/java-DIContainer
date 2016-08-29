package com.zonkil.di;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import com.zonkil.di.examples.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class ContainerTest {

    private Container container;

    @Before
    public void setUp() throws Exception {
        container = new Container();
    }

    @Test
    public void testCreateObject() throws Exception {
        SimpleClass sampleClass = container.createObject(SimpleClass.class.getTypeName(),SimpleClass.class);
        assertNotNull(sampleClass);
    }

    @Test(expected = ClassCastException.class)
    public void testCrateWrongObject() throws Exception{
        Container c = container.createObject(SimpleClass.class.getTypeName(),Container.class);
    }

    @Test(expected = ClassNotFoundException.class)
    public void testCreateUnexistingClass() throws Exception {
        SimpleClass sampleClass = container.createObject("jakas.bledna.nazwa",SimpleClass.class);
    }

    @Test(expected = InstantiationException.class)
    public void testCreatingInterfaceInstance() throws Exception{
        SimpleInterface sampleClass = container.createObject(SimpleInterface.class.getTypeName(), SimpleInterface.class);
    }

    @Test(expected = InstantiationException.class)
    public void testCreatingAbstractClass() throws Exception {
        SimpleAbstractClass sampleClass = container.createObject(SimpleAbstractClass.class.getTypeName(), SimpleAbstractClass.class);
    }

    @Test(expected = InstantiationException.class)
    public void testCreatingClassWithPrivateConstructor() throws Exception {
        SimpleClassWithPrivateConstructor sampleClass = container.createObject(SimpleClassWithPrivateConstructor.class.getTypeName(), SimpleClassWithPrivateConstructor.class);
    }

    @Test
    public void testCreatingClassImplementingInterface() throws Exception {
        SimpleInterface s = container.createObject(SimpleClassImplementingInterface.class.getTypeName(),SimpleInterface.class);
        assertNotNull(s);
    }

    @Test
    public void testCreatingClassExtendingAbstractClass() throws Exception{
        SimpleAbstractClass s = container.createObject(SimpleClassExtendingAbstractClass.class.getTypeName(), SimpleAbstractClass.class);
        assertNotNull(s);
    }

    @Test
    public void testCreatingClassWithParameter() throws Exception {
        ClassWithParameter c = container.createObject(ClassWithParameter.class.getTypeName(), ClassWithParameter.class);
        c.przedstawSie();
        assertNotNull(c);
    }

    @Test
    public void testCreatingClassWithInterfaceAsParameter() throws Exception{
        Map<String,String> classMapping = new HashMap<>();
        classMapping.put("com.zonkil.di.examples.SimpleInterface","com.zonkil.di.examples.SimpleClassImplementingInterface");
        container.setConfiguration(classMapping);

        ClassWithInterfaceAsParameter p = container.createObject(ClassWithInterfaceAsParameter.class.getTypeName(), ClassWithInterfaceAsParameter.class);
        assertNotNull(p);
    }

    @Test//(expected = Exception.class)
    public void testCreatingClassWithInterfaceAsParameterAndMissingParameterType() throws Exception{
        Map<String,String> classMapping = new HashMap<>();
        classMapping.put("bad.class","com.zonkil.di.examples.SimpleClassImplementingInterface");
        container.setConfiguration(classMapping);

        ClassWithInterfaceAsParameter p = container.createObject(ClassWithInterfaceAsParameter.class.getTypeName(), ClassWithInterfaceAsParameter.class);
    }

    @Test//(expected = Exception.class)
    public void testCreatingClassWithInterfaceAsParameterAndMissingCreationType() throws Exception{
        Map<String,String> classMapping = new HashMap<>();
        classMapping.put("com.zonkil.di.examples.SimpleInterface","bad.class.name");
        container.setConfiguration(classMapping);

        ClassWithInterfaceAsParameter p = container.createObject(ClassWithInterfaceAsParameter.class.getTypeName(), ClassWithInterfaceAsParameter.class);
    }

}