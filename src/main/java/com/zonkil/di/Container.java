package com.zonkil.di;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class Container {

    private ClassLoader classLoader;
    private Map<String,String> classMapping;
    private Map<String, String> configuration;

    public Container() {
        classLoader = ClassLoader.getSystemClassLoader();
    }


    public <T> T createObject(String className, Class<T> retClass) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> clazz = classLoader.loadClass(className);
        Constructor<?>[] constructors = clazz.getConstructors();
        Constructor c = findLongestConstructor(constructors);
        Parameter[] params = c.getParameters();
        Object[] paramsInstances = new Object[params.length];
        int i=0;
        for(Parameter p : params){
            Class<?> paramType = p.getType();
            String classToMake = paramType.getTypeName();
            if(paramType.isInterface()){
                classToMake = classMapping.get(paramType.getTypeName());
            }

            Object paramInstance = createObject(classToMake,paramType);
            paramsInstances[i] = paramInstance;
        }

        return (T) c.newInstance(paramsInstances);
    }

    public void setConfiguration(Map<String,String> configuration) {
        this.configuration = configuration;
    }

    private Constructor findLongestConstructor(Constructor[] constructors) throws InstantiationException {
        if(constructors.length==0){
            throw new InstantiationException("no avalible constructror");
        }
        Constructor longestConstructor = constructors[0];
        for(Constructor c : constructors){
            if(c.getParameterCount() > longestConstructor.getParameterCount())
                longestConstructor = c;
        }
        return longestConstructor;
    }


}
