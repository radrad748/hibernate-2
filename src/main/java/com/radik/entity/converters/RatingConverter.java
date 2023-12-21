package com.radik.entity.converters;

import com.radik.entity.enums.Rating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating rating) {
        if (rating == null) return null;
        return rating.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String rating) {
        if (rating == null) return null;
        for(Rating enumType : Rating.values()) {
            if (enumType.getValue().equals(rating)) return enumType;
        }
        return null;
    }
}
