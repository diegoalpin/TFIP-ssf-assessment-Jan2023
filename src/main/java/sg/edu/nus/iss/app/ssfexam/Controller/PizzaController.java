package sg.edu.nus.iss.app.ssfexam.Controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.app.ssfexam.Model.Order;

@Controller
@RequestMapping
public class PizzaController {

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
        return "view1";

    }

}
