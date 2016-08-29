package com.zonkil.di.annotations;

/**
 * Created by zonkil on 19.05.2016.
 */

import javax.xml.stream.events.EndElement;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.CONSTRUCTOR, ElementType.PARAMETER})
public @interface Inject {
}

