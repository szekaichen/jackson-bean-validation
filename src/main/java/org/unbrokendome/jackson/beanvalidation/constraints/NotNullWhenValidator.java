package org.unbrokendome.jackson.beanvalidation.constraints;

import org.unbrokendome.jackson.beanvalidation.ValidationContextHolder;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class NotNullWhenValidator implements ConstraintValidator<NotNullWhen, Object> {

    private String field;
    private String value;

    @Override
    public void initialize(NotNullWhen notBlankWhen) {
        field = notBlankWhen.field();
        value = notBlankWhen.value();
    }

    @Override
    public boolean isValid(Object data, ConstraintValidatorContext context) {
        Object actualValue = ValidationContextHolder.getHints().get(field);
        return actualValue == null ||
                !Objects.equals(actualValue.toString(), value) ||
                data != null;
    }
}
