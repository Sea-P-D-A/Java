package ru.vsu.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Repository<ID, Entity> {
    private final EntityMetaData entityMetaData;
    private Locale FORMAT_LOCALE;

    public Repository(EntityContext entityContextLoader, Class<Entity> typeClass) {
        this.entityMetaData = entityContextLoader.getEntityMetaData(typeClass);
    }

    public String generateSelectQuery(ID id) {
        // Подставляем ID в шаблон
        String template = entityMetaData.getSelectByQueryTemplate();
        return String.format(template, id);
    }

    public String generateInsertQuery(Entity entity) {
        String template = entityMetaData.getInsertQueryTemplate();
        List<Object> values = new ArrayList<>();

        for (Field field : entityMetaData.getFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(entity);

                if (value instanceof Float) {
                    value = String.format("%.6f", value).replace('.', ',');
                    template = template.replaceFirst("%f", (String) value);
                    continue;
                } else if (value instanceof String) {
                    template = template.replaceFirst("'%s'", "'" + value + "'");
                    continue;
                } else if (value instanceof Boolean) {
                    template = template.replaceFirst("%b", value.toString().toLowerCase());
                    continue;
                }

                values.add(value);

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return values.isEmpty() ? template : String.format(template, values.toArray());
    }
}