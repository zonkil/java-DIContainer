package com.zonkil.di.examples;

import com.zonkil.di.annotations.Bean;
import com.zonkil.di.annotations.Inject;

/**
 * Created by zonkil on 19.05.2016.
 */
public class SampleClass implements SampleInterface {

    @Inject
    private String napis;

    @Inject
    @Bean
    private SampleInterface sampleInterface;


    private String bezAnnotacji;

    @Bean
    private String tylkoInnaAnnotacja;



    public void saySomething(){
        System.out.printf("Sample class");
    }

    @Override
    public void seyHello() {
        saySomething();
    }
}
