package com.radik.entity.converters;

import com.radik.entity.enums.SpecialFeatures;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Converter(autoApply = true)
public class SpecialFeaturesConverter implements AttributeConverter<Set<SpecialFeatures>, String> {
    @Override
    public String convertToDatabaseColumn(Set<SpecialFeatures> specialFeatures) {
        if (specialFeatures == null) return null;
        if (specialFeatures.isEmpty()) return null;
        if (specialFeatures.size() == 1) return specialFeatures.iterator().next().getValue();

        StringBuilder builder = new StringBuilder();
        Iterator<SpecialFeatures> iterator = specialFeatures.iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next().getValue());
            if (iterator.hasNext()) builder.append(",");
        }
        return builder.toString();
    }

    @Override
    public Set<SpecialFeatures> convertToEntityAttribute(String setSpeacialFeatures) {
        if (setSpeacialFeatures == null) return null;

        Set<SpecialFeatures> set = new HashSet<>();
        if (!setSpeacialFeatures.contains(",")) {
            for (SpecialFeatures enumType : SpecialFeatures.values()) {
                if (enumType.getValue().equals(setSpeacialFeatures)) {
                    set.add(enumType);
                    return set;
                }
            }
        }

        String[] specialFeatures = setSpeacialFeatures.split(",");
        for (String str : specialFeatures) {
            for (SpecialFeatures enumType : SpecialFeatures.values()) {
                if (enumType.getValue().equals(str)) {
                    set.add(enumType);
                    break;
                }
            }
        }
        return set;
    }
}
