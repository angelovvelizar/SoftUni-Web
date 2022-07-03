package com.example.coffeeshop.web;

import com.example.coffeeshop.model.view.OrderViewModel;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final OrderService orderService;
    private final CurrentUser currentUser;
    private final UserService userService;

    public HomeController(OrderService orderService, CurrentUser currentUser, UserService userService) {
        this.orderService = orderService;
        this.currentUser = currentUser;
        this.userService = userService;
    }


    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }

        List<OrderViewModel> orders = this.orderService.findAllOrdersByPrice();

        model.addAttribute("orders", orders);

        model.addAttribute("totalTime", orders
                .stream()
                .map(orderViewModel -> orderViewModel.getCategory().getNeededTime())
                .reduce(Integer::sum).orElse(0));

        model.addAttribute("employees", this.userService.findAllUsersByCountOfOrders());

        return "home";
    }
}
