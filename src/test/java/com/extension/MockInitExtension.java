package com.extension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Field;

public class MockInitExtension implements TestInstancePostProcessor {

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
        for (Field field : testInstance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Mock.class)) {
                field.setAccessible(true);
                try {
                    Object mock = Mockito.mock(field.getType());
                    field.set(testInstance, mock);
                } catch (IllegalAccessException e) {
                    System.err.println("Не удалось инжектировать мок в поле " + field.getName() + ": " + e.getMessage());
                }
            }
        }
    }
}
