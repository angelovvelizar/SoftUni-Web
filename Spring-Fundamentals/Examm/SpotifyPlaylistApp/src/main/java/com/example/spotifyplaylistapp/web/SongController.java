package com.example.spotifyplaylistapp.web;

import com.example.spotifyplaylistapp.model.binding.SongBindingModel;
import com.example.spotifyplaylistapp.model.service.SongServiceModel;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final SongService songService;

    public SongController(UserService userService, ModelMapper modelMapper, SongService songService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.songService = songService;
    }

    @GetMapping("/add")
    public String add() {
        if (!this.userService.userIsLoggedIn()) {
            return "redirect:/";
        }

        return "song-add";
    }

    @PostMapping("/add")
    public String addPost(@Valid SongBindingModel songBindingModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || this.songService.performerExists(songBindingModel.getPerformer())) {
            redirectAttributes.addFlashAttribute("songBindingModel", songBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.songBindingModel", bindingResult);

            return "redirect:add";
        }

        SongServiceModel songServiceModel = this.modelMapper.map(songBindingModel, SongServiceModel.class);
        this.songService.addSong(songServiceModel);

        return "redirect:/";
    }


    @GetMapping("/add/{id}")
    public String addSong(@PathVariable Long id) {
        this.userService.addSong(id);
        return "redirect:/";
    }

    @GetMapping("/removeAll")
    public String removeAll() {
        this.userService.removeAllSongs();
        return "redirect:/";
    }

    @ModelAttribute
    public SongBindingModel songBindingModel() {
        return new SongBindingModel();
    }
}
