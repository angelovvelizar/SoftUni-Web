package com.example.shoppinglist.web;

import com.example.shoppinglist.model.view.ProductViewModel;
import com.example.shoppinglist.security.CurrentUser;
import com.example.shoppinglist.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final ProductService productService;

    public HomeController(CurrentUser currentUser, ProductService productService) {
        this.currentUser = currentUser;
        this.productService = productService;
    }


    @GetMapping("/")
    public String index(Model model){
        if(currentUser.getId() == null){
            return "index";
        }

        List<ProductViewModel> products = this.productService.getAllProducts();
        model.addAttribute("products", products);

        BigDecimal totalPrice = this.productService.getAllProducts()
                .stream()
                .map(ProductViewModel::getPrice)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        model.addAttribute("totalPrice", totalPrice);

        return "home";
    }


}
