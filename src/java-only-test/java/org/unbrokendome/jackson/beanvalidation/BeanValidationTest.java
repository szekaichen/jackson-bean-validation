package org.unbrokendome.jackson.beanvalidation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

class BeanValidationTest {

    @Test
    void testBeanValidation() {
        String invalidJson = "{\"not_null\": null}";

        ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new BeanValidationModule(validatorFactory));

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            objectMapper.readValue(invalidJson, TestSerializable.class);
        });
    }

    @JsonValidated
    public static class TestSerializable {
        @NotNull
        private final String notNull;

        @JsonCreator
        public TestSerializable(
                @NotNull @JsonProperty(value = "not_null", required = true) String notNull
        ) {
            this.notNull = notNull;
        }
    }
}