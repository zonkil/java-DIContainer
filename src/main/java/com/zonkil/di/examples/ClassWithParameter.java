package com.zonkil.di.examples;

import sun.java2d.pipe.SpanShapeRenderer;

/**
 * Created by zonkil on 14.06.2016.
 */
public class ClassWithParameter {
    private SimpleClass sc;

    public ClassWithParameter(SimpleClass sc) {
        this.sc = sc;
    }

    public void przedstawSie(){
        sc.przedstawSie();
        System.out.println(ClassWithParameter.class.getName());
    }
}
