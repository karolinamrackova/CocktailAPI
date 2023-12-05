package project.week0project.dto;

import java.lang.reflect.Field;

public class DynamicRequestDTO {

    public void shapeFields(Object requestBody) {
        Class<?> requestClass = requestBody.getClass();

        // Use reflection to get fields from the request class
        Field[] fields = requestClass.getDeclaredFields();

        // Dynamically add fields to the DynamicRequestDTO
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                this.getClass().getField(field.getName()).set(this, field.get(requestBody));
            } catch (IllegalAccessException | NoSuchFieldException e) {
                // Handle exceptions if necessary
            }
        }
    }
}