package sg.edu.nus.iss.app.ssfexam.Service;

import java.io.IOError;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.app.ssfexam.Model.Order;

@Service
public class PizzaService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void saveOrder(Order order) {
        redisTemplate.opsForValue().set(order.getId(), order.toJSON().toString());
    }

    public String getOrder(String orderId) throws IOException {
        String orderString = redisTemplate.opsForValue().get(orderId);
        Order orderObject = Order.create(orderString);
        // System.out.println(orderObject.toJSON().toString());
        return orderString;
    }
}
