package com.example.Atlas.infra;

import com.example.Atlas.model.UserRoles;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRoles, String> {

    @Override
    public String convertToDatabaseColumn(UserRoles role) {
        if (role == null) {
            return null;
        } return role.name().toLowerCase();
    }

    @Override
    public UserRoles convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (UserRoles role : UserRoles.values()) {
            if (role.name().equalsIgnoreCase(dbData)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + dbData);
    }
}
