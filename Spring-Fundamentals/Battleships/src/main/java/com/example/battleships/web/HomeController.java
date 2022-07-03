package com.example.battleships.web;

import com.example.battleships.model.binding.FireBindingModel;
import com.example.battleships.model.view.ShipViewModel;
import com.example.battleships.security.CurrentUser;
import com.example.battleships.service.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final ShipService shipService;

    public HomeController(CurrentUser currentUser, ShipService shipService) {
        this.currentUser = currentUser;
        this.shipService = shipService;
    }


    @GetMapping("/")
    public String index(Model model){
        if(this.currentUser.getId() == null) {
            return "index";
        }

        List<ShipViewModel> ships = this.shipService.getAllShipsByStatus();
        model.addAttribute("ships", ships);

        List<ShipViewModel> defenderShips = this.shipService.getAllDefenderShips();
        model.addAttribute("defenderShips", defenderShips);

        List<ShipViewModel> attackerShips = this.shipService.getAllAttackerShips();
        model.addAttribute("attackerShips", attackerShips);


        return "home";
    }

    @PostMapping("/")
    public String battle(@Valid FireBindingModel fireBindingModel,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute(
                            "fireBindingModel", fireBindingModel)
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.fireBindingModel", bindingResult);

            return "redirect:/";
        }

        this.shipService.fight(fireBindingModel);

        return "redirect:/";
    }


    @ModelAttribute
    public FireBindingModel fireBindingModel(){
        return new FireBindingModel();
    }


}
