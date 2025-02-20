package com.extension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.lang.reflect.Constructor;

public class MockitoExtension implements TestInstancePostProcessor {

    @Override
    public void postProcessTestInstance(Object o, ExtensionContext extensionContext) {
        for (Field field : o.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Mock.class)) {
                field.setAccessible(true);

                if (field.getType().isInterface() || field.getType().getModifiers() == 0) {
                    try {
                        Object mock = Mockito.mock(field.getType());
                        field.set(o, mock);
                    } catch (Exception e) {
                        System.err.println("Ошибка при мокации поля " + field.getName() + ": " + e.getMessage());
                    }
                } else {
                    Constructor<?> constructor = null;
                    try {
                        constructor = field.getType().getConstructor(int.class);
                        Object instance = constructor.newInstance(1);
                        field.set(o, instance);
                    } catch (Exception e) {
                        System.err.println("Не удалось создать объект для поля " + field.getName() + ": " + e.getMessage());
                    }
                }
            }
        }
    }
}
