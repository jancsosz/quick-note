package jpa;

import priority.Priority;

import javax.persistence.AttributeConverter;


/**
 * Converter class for Note Entity class' Priority enum.
 */
public class PriorityConverter implements AttributeConverter<Priority, String> {

    @Override
    public String convertToDatabaseColumn(Priority priority) {
        return priority.toString();
    }

    @Override
    public Priority convertToEntityAttribute(String s) {
        switch(s) {
            case "LOW":
                return Priority.LOW;
            case "NORMAL":
                return Priority.NORMAL;
            case "HIGH":
                return Priority.HIGH;
            case "VERY_HIGH":
                return Priority.VERY_HIGH;
            case "VERY_LOW":
                return Priority.VERY_LOW;
            default:
                throw new IllegalArgumentException("Hibás priority");
        }
    }
}
