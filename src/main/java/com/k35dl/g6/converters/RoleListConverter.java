package com.k35dl.g6.converters;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.k35dl.g6.models.User;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleListConverter implements AttributeConverter<List<User.Role>, String> {

    @Override
    public String convertToDatabaseColumn(List<User.Role> roles) {
        return roles.stream()
                .map(User.Role::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<User.Role> convertToEntityAttribute(String rolesString) {
        return Arrays.stream(rolesString.split(","))
                .map(User.Role::valueOf)
                .collect(Collectors.toList());
    }
}

