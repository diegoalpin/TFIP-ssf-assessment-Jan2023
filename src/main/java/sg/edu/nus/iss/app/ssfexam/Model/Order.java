package sg.edu.nus.iss.app.ssfexam.Model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import sg.edu.nus.iss.app.ssfexam.Validation.PizzaSizeValidation;
import sg.edu.nus.iss.app.ssfexam.Validation.PizzaTypeValidation;

public class Order {

    @NotNull(message = "Please select pizza type")
    @PizzaTypeValidation // custom
    private String type;

    @NotNull(message = "Please select pizza size")
    @PizzaSizeValidation // custom
    private String size;

    @Min(value = 1, message = "Please order 1-10 pizza")
    @Max(value = 10, message = "Please order 1-10 pizza")
    @NotNull(message = "Please order 1-10 pizza")
    private Integer quantity;

    public Order(@NotNull(message = "Please select pizza type") String type,
            @NotNull(message = "Please select pizza size") String size,
            @Min(value = 1, message = "Please order 1-10 pizza") @Max(value = 10, message = "Please order 1-10 pizza") Integer quantity) {
        this.type = type;
        this.size = size;
        this.quantity = quantity;
    }

    public Order() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
