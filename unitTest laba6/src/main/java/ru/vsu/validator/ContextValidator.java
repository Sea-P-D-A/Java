package ru.vsu.validator;

import ru.vsu.api.Entity;
import ru.vsu.api.Id;
import ru.vsu.exceptions.ContextException;

import java.lang.reflect.Field;
import java.util.Set;

public class ContextValidator {

    public void validateIsAnnotatedWithEntity(Set<Class<?>> classes) {

        for (Class<?> tmp : classes) {
            Entity entityAnotation = tmp.getAnnotation(Entity.class);

            if (entityAnotation == null) {
                throw new ContextException("Класс: " + tmp.getSimpleName() + " не имеет анотации Entity");
            }
        }

    }


    public void validateHasOnlySupportedTypes(Set<Class<?>> validTypes, Set<Class<?>> entities) {

        for (Class<?> tmp : entities) {

            Field[] fields = tmp.getDeclaredFields();


            for (Field field : fields) {
                Class<?> type = field.getType();

                if (!validTypes.contains(type)) {
                    throw new ContextException("Класс: " + tmp.getSimpleName() + " Содержит недопустимые типы:" + type.getName() + "Поле: " + field.getName());
                }
            }
        }

    }

    public void validateHasId(Set<Class<?>> entities) {

        for (Class<?> tmp : entities) {
            int countId = 0;
            Field[] fields = tmp.getDeclaredFields();

            for (Field field : fields) {
                if (field.isAnnotationPresent(Id.class)) {
                    countId++;

                }
            }

            if (countId != 1) {
                throw new ContextException("Класс " + tmp.getName() + " содержит " + countId + " полей с аннотацией @Id (должно быть ровно одно)");
            }

        }
    }
}
