package sg.edu.nus.iss.app.ssfexam.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.app.ssfexam.Model.Order;
import sg.edu.nus.iss.app.ssfexam.Service.PizzaService;

@RestController
public class PizzaRestController {
    @Autowired
    PizzaService pizzaService;

    @GetMapping(path = "/order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOrder(@PathVariable String orderId) throws IOException {
        // try {
        // Order order = pizzaService.getOrder(orderId);
        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // }
        Order order = pizzaService.getOrder(orderId);
        System.out.println(order.toString());
        if (order == null) {

            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("message", "Order " + orderId + " not found").build();
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonObject.toString());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(order.toJSON().toString());
    }
}
