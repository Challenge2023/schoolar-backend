package com.schoolar.schoolarAPI.annotations.validators;

import com.schoolar.schoolarAPI.annotations.IsEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IsEnumValidator implements ConstraintValidator<IsEnum, Enum<?>> {
    private List<String> acceptedValues;

    @Override
    public void initialize(IsEnum constraintAnnotation) {
        acceptedValues = Arrays.stream(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || acceptedValues.contains(value.name());
    }
}
