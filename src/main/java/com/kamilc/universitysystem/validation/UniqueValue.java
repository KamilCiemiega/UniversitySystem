package com.kamilc.universitysystem.validation;

public @interface UniqueValue {
    String message() default "Field value must be unique";
    Class<?> entity();
    String fieldName();
    String valueField();
}
