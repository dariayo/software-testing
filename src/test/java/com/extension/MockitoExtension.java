package com.extension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.mockito.Mockito;

import java.lang.reflect.Field;

public class MockitoExtension implements TestInstancePostProcessor {
    @Override
    public void postProcessTestInstance(Object o, ExtensionContext extensionContext) throws Exception {
        for (Field field : o.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Mock.class)) {
                field.setAccessible(true);
                Object mock = Mockito.mock(field.getType());
                field.set(o, mock);
            }
        }
    }
}
