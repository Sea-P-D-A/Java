package ru.vsu.core;

import ru.vsu.validator.ContextValidator;
import ru.vsu.exceptions.ContextException;
import java.lang.reflect.Field;
import java.util.*;

import static ru.vsu.configuration.DefaultConfiguration.DEFAULT_SUPPORTED_TYPES;

public class EntityContext {
    private final ContextValidator contextValidator;
    private final Map<Class<?>, EntityMetaData> classNameToQueryMetadata = new HashMap<>();

    public EntityContext(ContextValidator contextValidator) {
        this.contextValidator = contextValidator;
    }

    public void loadContext(Set<Class<?>> classes) {
        contextValidator.validateIsAnnotatedWithEntity(classes);
        contextValidator.validateHasOnlySupportedTypes(DEFAULT_SUPPORTED_TYPES, classes);
        contextValidator.validateHasId(classes);

        for (Class<?> clazz : classes) {
            EntityMetaData metaData = createMetaData(clazz);
            classNameToQueryMetadata.put(clazz, metaData);
        }
    }

    private EntityMetaData createMetaData(Class<?> clazz) {
        String tableName = clazz.getSimpleName().toLowerCase();

        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        String selectTemplate = generateSelectTemplate(tableName, fields);
        String insertTemplate = generateInsertTemplate(tableName, fields);

        return new EntityMetaData(selectTemplate, insertTemplate, tableName, fields);
    }

    private String generateSelectTemplate(String tableName, List<Field> fields) {
        StringBuilder columns = new StringBuilder();

        for (int i = 0; i < fields.size(); i++) {
            if (i > 0) columns.append(", ");
            columns.append(convertToSnakeCase(fields.get(i).getName()));
        }

        return String.format("SELECT %s FROM %s WHERE id = %%d",
                columns.toString(), tableName);
    }

    private String generateInsertTemplate(String tableName, List<Field> fields) {
        // "INSERT INTO user (id, name, age) VALUES (%d, '%s', %d)"
        StringBuilder columnNames = new StringBuilder();
        StringBuilder valuePlaceholders = new StringBuilder();

        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            String columnName = convertToSnakeCase(field.getName());

            if (i > 0) {
                columnNames.append(", ");
                valuePlaceholders.append(", ");
            }

            columnNames.append(columnName);
            valuePlaceholders.append(getSqlFormatSpecifier(field.getType()));
        }

        return String.format("INSERT INTO %s (%s) VALUES (%s)",
                tableName, columnNames.toString(), valuePlaceholders.toString());
    }

    private String convertToSnakeCase(String camelCase) {
        return camelCase.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

    private String getSqlFormatSpecifier(Class<?> type) {
        if (type.equals(String.class)) {
            return "'%s'";
        } else if (type.equals(Float.class) || type.equals(float.class)) {
            return "%f";
        } else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
            return "%b";
        } else {
            return "%d";
        }
    }

    public EntityMetaData getEntityMetaData(Class<?> entityType) {
        return classNameToQueryMetadata.get(entityType);
    }
}