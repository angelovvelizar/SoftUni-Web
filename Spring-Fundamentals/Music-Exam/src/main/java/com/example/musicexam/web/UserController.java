package com.example.musicexam.web;

import com.example.musicexam.model.binding.UserLoginBindingModel;
import com.example.musicexam.model.binding.UserRegisterBindingModel;
import com.example.musicexam.model.service.UserServiceModel;
import com.example.musicexam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/register")
    public String register() {
        if(this.userService.userIsLoggedIn()){
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())
                || this.userService.userExistsByUsername(userRegisterBindingModel.getUsername())
                || this.userService.userExistsByEmail(userRegisterBindingModel.getEmail())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);
        return "redirect:login";
    }


    @GetMapping("/login")
    public String login(Model model) {
        if(this.userService.userIsLoggedIn()){
            return "redirect:/";
        }

        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@Valid UserLoginBindingModel userLoginBindingModel,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);

            return "redirect:login";
        }

        if (!this.userService.userExistsByUsernameAndPassword(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword())) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("isFound", false);
            return "redirect:login";
        }

        UserServiceModel userServiceModel = this.modelMapper.map(userLoginBindingModel, UserServiceModel.class);
        this.userService.loginUser(userServiceModel);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(){
        this.userService.logout();
        return "redirect:/";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }
}
