package com.example.battleships.web;

import com.example.battleships.model.binding.ShipAddBindingModel;
import com.example.battleships.model.service.ShipServiceModel;
import com.example.battleships.service.ShipService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/ships")
public class ShipController {
    private final ShipService shipService;
    private final ModelMapper modelMapper;

    public ShipController(ShipService shipService, ModelMapper modelMapper) {
        this.shipService = shipService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(){

        return "ship-add";
    }

    @PostMapping("/add")
    public String addPost(@Valid ShipAddBindingModel shipAddBindingModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors() || this.shipService.shipExists(shipAddBindingModel.getName())){
            redirectAttributes.addFlashAttribute("shipAddBindingModel", shipAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipAddBindingModel", bindingResult);

            return "redirect:add";
        }

        ShipServiceModel shipServiceModel = this.modelMapper.map(shipAddBindingModel, ShipServiceModel.class);
        this.shipService.addShip(shipServiceModel);


        return "redirect:/";
    }

    @ModelAttribute
    public ShipAddBindingModel shipAddBindingModel(){
        return new ShipAddBindingModel();
    }
}
