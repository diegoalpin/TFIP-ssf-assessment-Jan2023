package sg.edu.nus.iss.app.ssfexam.Validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PizzaTypeValidator.class)
public @interface PizzaTypeValidation {
    // error message
    public String message() default "Pizza Type is wrong";

    // represents group of constraints
    public Class<?>[] groups() default {};

    // represents additional information about annotation
    public Class<? extends Payload>[] payload() default {};
}
