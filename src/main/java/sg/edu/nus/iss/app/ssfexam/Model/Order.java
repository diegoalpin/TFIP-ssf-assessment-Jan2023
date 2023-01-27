package sg.edu.nus.iss.app.ssfexam.Model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import sg.edu.nus.iss.app.ssfexam.Validation.PizzaSizeValidation;
import sg.edu.nus.iss.app.ssfexam.Validation.PizzaTypeValidation;

public class Order {

    private String id;
    private Float cost;

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

    private Delivery delivery;

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Order(@NotNull(message = "Please select pizza type") String type,
            @NotNull(message = "Please select pizza size") String size,
            @Min(value = 1, message = "Please order 1-10 pizza") @Max(value = 10, message = "Please order 1-10 pizza") Integer quantity) {
        this.type = type;
        this.size = size;
        this.quantity = quantity;
    }

    public Order() {
        // empty
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

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = generateId(8);
    }

    private synchronized String generateId(int numChars) {
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while (strBuilder.length() < numChars) {
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder.toString().substring(0, numChars);
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()

                .add("orderId", this.getId())
                .add("name", this.getDelivery().getName())
                .add("address", this.getDelivery().getAddress())
                .add("phone", this.getDelivery().getPhone())
                .add("rush", this.getDelivery().getIsRush())
                .add("comments", this.getDelivery().getComments())
                .add("pizza", this.getType())
                .add("size", this.getSize())
                .add("quantity", this.getQuantity())
                .add("total", this.getCost())
                .build();
    }

    @Override
    public String toString() {
        return "Order [type=" + type + ", size=" + size + ", quantity=" + quantity + "]";
    }

    public static Order create(String orderString) throws IOException {
        Delivery delivery = new Delivery();
        Order order = new Order();

        if (orderString != null) {
            try (InputStream is = new ByteArrayInputStream(orderString.getBytes())) {
                JsonReader jsonReader = Json.createReader(is);
                JsonObject jsonObject = jsonReader.readObject();
                order.setId(jsonObject.getString("orderId"));
                order.setQuantity(jsonObject.getInt("quantity"));
                order.setSize(jsonObject.getString("size"));
                order.setType(jsonObject.getString("pizza"));

                delivery.setName(jsonObject.getString("name"));
                delivery.setAddress(jsonObject.getString("address"));
                delivery.setPhone(jsonObject.getString("phone"));
                delivery.setIsRush(jsonObject.getBoolean("rush"));
                delivery.setComments(jsonObject.getString("comments"));
                order.setDelivery(delivery);
            }
        }
        return order;
    }

    private void setId(String string) {
        this.id = string;
    }

}
