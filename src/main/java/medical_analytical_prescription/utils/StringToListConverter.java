package medical_analytical_prescription.utils;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringToListConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute == null) {
            return "";
        } else {
            return String.join(",", attribute);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return new ArrayList<>();
        } else {
            return new ArrayList<>(Arrays.asList(dbData.split(",")));
        }
    }
}
