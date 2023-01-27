package sg.edu.nus.iss.app.ssfexam.Validation;

import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component("pizzaSizeValidator")
public class PizzaSizeValidator implements ConstraintValidator<PizzaSizeValidation, String> {

    @Override
    public boolean isValid(String pizzaSize, ConstraintValidatorContext context) {

        if (pizzaSize != null) {

            if (pizzaSize.equalsIgnoreCase("sm") ||
                    pizzaSize.equalsIgnoreCase("md") ||
                    pizzaSize.equalsIgnoreCase("lg")) {
                return true;
            }

            return false;
        }
        return true;// if null, let not null handle the logic
    }

}
