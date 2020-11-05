package com.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD,CONSTRUCTOR,FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String name = "222";

    String[] value() default "aaa";
}
