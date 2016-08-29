package com.zonkil.di;

/**
 * Created by zonkil on 11.06.2016.
 */
public class MappedClass {

    private String annotatedClassName;
    private Class<?> annotatedClass;
    private String actualClassToCrateName;
    private Class<?> actualClass;

    public MappedClass(String annotatedClassName, String actualClassToCrateName){
        this.annotatedClassName = annotatedClassName;
        this.actualClassToCrateName = actualClassToCrateName;

        tryToLoadClass();
    }


    private void tryToLoadClass() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        try {
            annotatedClass = cl.loadClass(annotatedClassName);
            actualClass = cl.loadClass(actualClassToCrateName);
            if(actualClass.isInterface()){
                throw new RuntimeException("to jest interface");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        validateClassTree();
    }

    private boolean validateClassTree() throws RuntimeException {
        boolean lookFoInterface = annotatedClass.isInterface();

        if (lookFoInterface) {
            validateInterface();
        } else {
            validateClass();
        }
        return false;
    }

    private void validateInterface() throws RuntimeException {
        Class testedClass = actualClass;

        while (testedClass != null) {
            Class[] interfaces = testedClass.getInterfaces();
            for (Class inter : interfaces) {
                if (inter.equals(annotatedClass))
                    return;
            }
            testedClass = testedClass.getSuperclass();
        }
        throw new RuntimeException("klasa nie implementuje interfejsu");
    }

    void validateClass() throws RuntimeException {
        Class testedClass = actualClass;
        while (testedClass!=null){
            if(testedClass.equals(annotatedClass))
                return;
            testedClass = testedClass.getSuperclass();
        }
        throw new RuntimeException("klasa nie jest potomkiem danej klasy");
    }
}
