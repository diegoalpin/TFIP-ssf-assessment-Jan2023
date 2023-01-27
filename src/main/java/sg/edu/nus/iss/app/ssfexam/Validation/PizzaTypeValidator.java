package sg.edu.nus.iss.app.ssfexam.Validation;

import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component("pizzaTypeValidator")
public class PizzaTypeValidator implements ConstraintValidator<PizzaTypeValidation, String> {

    @Override
    public boolean isValid(String pizzaType, ConstraintValidatorContext context) {
        if (pizzaType != null) {

            if (pizzaType.equalsIgnoreCase("bella") ||
                    pizzaType.equalsIgnoreCase("margherita") ||
                    pizzaType.equalsIgnoreCase("marinara") ||
                    pizzaType.equalsIgnoreCase("spianatacalabrese") ||
                    pizzaType.equalsIgnoreCase("trioformaggio")) {
                return true;
            }
            return false;
        }
        return true;// if null, let not null handle the logic
    }

}
