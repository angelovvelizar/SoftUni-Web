package softuni.bg.pathfinder.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.bg.pathfinder.model.binding.UserLoginBindingModel;
import softuni.bg.pathfinder.model.binding.UserRegisterBindingModel;
import softuni.bg.pathfinder.model.service.UserServiceModel;
import softuni.bg.pathfinder.model.view.UserViewModel;
import softuni.bg.pathfinder.service.UserService;

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

    @ModelAttribute("userRegisterBindingModel")
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }

    @ModelAttribute("userLoginBindingModel")
    public UserLoginBindingModel userLoginBindingModel(){
        return new UserLoginBindingModel();
    }

    @GetMapping("/register")
    public String register(Model model){
        if(!model.containsAttribute("userNameExists")){
            model.addAttribute("userNameExists", false);
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        boolean usernameExists = this.userService.usernameExists(userRegisterBindingModel.getUsername());
        if(usernameExists){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("userNameExists", true);

            return "redirect:register";
        }

        this.userService.registerUser(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model){
        if(!model.containsAttribute("isExists")){
            model.addAttribute("isExists", true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);

            return "redirect:login";
        }

        UserServiceModel user = userService.findUserByUsernameAndPassword(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());
        if(user == null){
            redirectAttributes.addFlashAttribute("isExists", false);
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);

            return "redirect:login";
        }

        this.userService.loginUser(user.getId(), user.getUsername());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(){
        this.userService.logout();
        return "redirect:/";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id, Model model){
        UserViewModel user = this.modelMapper.map(this.userService.findById(id), UserViewModel.class);
        model.addAttribute("user", user);

        return "profile";
    }




}
