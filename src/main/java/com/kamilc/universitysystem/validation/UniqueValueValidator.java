package com.kamilc.universitysystem.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Component
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private final ApplicationContext applicationContext;

    Class<?> entityClass;
    String fieldName;
    String valueField;

    public UniqueValueValidator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.entityClass = constraintAnnotation.entity();
        this.fieldName = constraintAnnotation.fieldName();
        this.valueField = constraintAnnotation.valueField();
    }

    @Override
    public boolean isValid(Object dto, ConstraintValidatorContext context) {
        try {
            Field valueFieldObj = dto.getClass().getDeclaredField(valueField);
            valueFieldObj.setAccessible(true);
            Object valueToCheck = valueFieldObj.get(dto);

            if (valueToCheck == null) return true;

            String repoBeanName = Introspector.decapitalize(entityClass.getSimpleName()) + "Repository";
            Object repository = applicationContext.getBean(repoBeanName);

            String methodName = "findBy" + capitalize(fieldName);
            Method method = repository.getClass().getMethod(methodName, valueToCheck.getClass());
            Object result = method.invoke(repository, valueToCheck);

            return result == null;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String capitalize(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
