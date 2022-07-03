package com.example.coffeeshop.web;

import com.example.coffeeshop.model.binding.OrderBindingModel;
import com.example.coffeeshop.model.service.OrderServiceModel;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final ModelMapper modelMapper;
    private final OrderService orderService;
    private final CurrentUser currentUser;

    public OrderController(ModelMapper modelMapper, OrderService orderService, CurrentUser currentUser) {
        this.modelMapper = modelMapper;
        this.orderService = orderService;
        this.currentUser = currentUser;
    }


    @GetMapping("/add")
    public String addOrder(){
        if(currentUser.getId() == null){
            return "redirect:/";
        }

        return "order-add";
    }

    @PostMapping("/add")
    public String addOrderPost(@Valid OrderBindingModel orderBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("orderBindingModel", orderBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderBindingModel", bindingResult);

            return "redirect:add";
        }

        OrderServiceModel orderServiceModel = this.modelMapper.map(orderBindingModel, OrderServiceModel.class);

        this.orderService.addOrder(orderServiceModel);

        return "redirect:/";
    };

    @GetMapping("/ready/{id}")
    public String ready(@PathVariable Long id){
        this.orderService.readyOrder(id);

        return "redirect:/";
    }


    @ModelAttribute
    public OrderBindingModel orderBindingModel(){
        return new OrderBindingModel();
    }
}
