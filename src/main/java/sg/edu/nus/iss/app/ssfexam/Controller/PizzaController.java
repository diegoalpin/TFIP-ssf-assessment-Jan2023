package sg.edu.nus.iss.app.ssfexam.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.app.ssfexam.Model.Delivery;
import sg.edu.nus.iss.app.ssfexam.Model.Order;
import sg.edu.nus.iss.app.ssfexam.Service.PizzaService;

@Controller
@RequestMapping
public class PizzaController {
    @Autowired
    PizzaService pizzaService;

    @GetMapping(path = "/")
    public String showPizzaForm(Model model) {
        model.addAttribute("order", new Order());
        return "index";
    }

    @PostMapping(path = "/pizza", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
    public String selectPizza(@Valid Order order, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("order", order);
            return "index";
        }
        System.out.println("Order is " + order.toString());
        order.setDelivery(new Delivery());

        model.addAttribute("order", order);
        return "view1";

    }

    @PostMapping(path = "/pizza/order", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
    public String submitOrder(@Valid Order order, BindingResult bindingResult, Model model) {
        // System.out.println("order is" + model.getAttribute("order").toString());
        // System.out.println("delivery is " + delivery.toString());
        System.out.println(order.getDelivery().toString());
        System.out.println(order.toString());

        // 1. Generate UUID
        order.setId();
        System.out.println("order id is" + order.getId());

        // 2. Calculate Order Cost
        Integer unitCost = 0;
        Float multiplier = 1f;
        Integer rushCost = 0;
        Float costNoRush = 0f;
        if (order.getType().equals("bella") || order.getType().equals("marinara")
                || order.getType().equals("spianatacalabrese")) {
            unitCost = 30;
        } else if (order.getType().equals("margherita")) {
            unitCost = 22;
        } else if (order.getType().equals("trioformaggio")) {
            unitCost = 25;
        }

        if (order.getSize().equals("sm")) {
            multiplier = 1f;
        } else if (order.getSize().equals("md")) {
            multiplier = 1.2f;
        } else if (order.getSize().equals("lg")) {
            multiplier = 1.5f;
        }

        if (order.getDelivery().getIsRush() == true) {
            rushCost = 2;
        } else {
            rushCost = 0;
        }
        System.out.println(unitCost + ", " + multiplier + ", " + rushCost);
        order.setCost((unitCost * multiplier * order.getQuantity()) + rushCost);
        System.out.println(order.getCost());
        costNoRush = unitCost * multiplier * order.getQuantity();

        model.addAttribute("order", order);
        model.addAttribute("rushCost", rushCost);
        model.addAttribute("costNoRush", costNoRush);
        // 3. Persist the order

        pizzaService.saveOrder(order);

        return "view2";
    }

    // @GetMapping(path = "/order/{orderId}")
    // public String getOrder(@PathVariable String orderId, Model model) {
    // try {
    // Order order = pizzaService.getOrder(orderId);
    // model.addAttribute("order", order);
    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // }

    // return "view2";
    // }

}
