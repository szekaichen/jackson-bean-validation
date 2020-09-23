package org.unbrokendome.jackson.beanvalidation.constraints;

import org.unbrokendome.jackson.beanvalidation.ValidationContextHolder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class NotBlankWhenValidator implements ConstraintValidator<NotBlankWhen, String> {

    private String field;
    private String value;

    @Override
    public void initialize(NotBlankWhen notBlankWhen) {
        field = notBlankWhen.field();
        value = notBlankWhen.value();
    }

    @Override
    public boolean isValid(String data, ConstraintValidatorContext context) {
        Object actualValue = ValidationContextHolder.getHints().get(field);
        return actualValue == null ||
                !Objects.equals(actualValue.toString(), value) ||
                !(data == null || data.trim().isEmpty());
    }
}
