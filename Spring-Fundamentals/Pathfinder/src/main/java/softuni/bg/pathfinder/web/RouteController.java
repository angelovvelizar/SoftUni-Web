package softuni.bg.pathfinder.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.bg.pathfinder.model.binding.RouteBindingModel;
import softuni.bg.pathfinder.model.service.RouteServiceModel;
import softuni.bg.pathfinder.model.view.RouteViewModel;
import softuni.bg.pathfinder.service.RouteService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {
    private final RouteService routeService;
    private final ModelMapper modelMapper;

    public RouteController(RouteService routeService, ModelMapper modelMapper) {
        this.routeService = routeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String allRoutes(Model model) {
        List<RouteViewModel> routes = this.routeService.findAllRoutes();
        model.addAttribute("routes", routes);

        return "routes";
    }

    @GetMapping("/details/{id}")
    public String routeDetails(@PathVariable Long id, Model model) {
        RouteViewModel route = this.routeService.findRouteById(id);
        model.addAttribute("route", route);

        return "route-details";
    }

    @GetMapping("/add")
    public String addRoute() {
        return "add-route";
    }

    @PostMapping("/add")
    public String addRoutePost(@Valid RouteBindingModel routeBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors() || this.routeService.existsByName(routeBindingModel.getName())) {
            redirectAttributes.addFlashAttribute("routeBindingModel", routeBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.routeBindingModel", bindingResult);

            return "redirect:add";
        }

        RouteServiceModel routeServiceModel = this.modelMapper.map(routeBindingModel, RouteServiceModel.class);
        routeServiceModel.setGpxCoordinates(new String(routeBindingModel.getGpxCoordinates().getBytes()));

        this.routeService.addNewRoute(routeServiceModel);

        return "redirect:all";
    }

    @ModelAttribute
    public RouteBindingModel routeBindingModel() {
        return new RouteBindingModel();
    }
}
