package com.zonkil.di;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zonkil on 12.06.2016.
 */
public class Configuration {

    private Map<String, MappedClass> configureClasses;

    public Configuration(){
        configureClasses = new ConcurrentHashMap<>();
        configureClasses.put("",new MappedClass("",""));
    }
}
