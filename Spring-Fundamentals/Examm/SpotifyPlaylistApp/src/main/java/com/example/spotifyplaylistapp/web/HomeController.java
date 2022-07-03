package com.example.spotifyplaylistapp.web;

import com.example.spotifyplaylistapp.model.entity.SongEntity;
import com.example.spotifyplaylistapp.model.entity.UserEntity;
import com.example.spotifyplaylistapp.model.view.SongViewModel;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final UserService userService;
    private final SongService songService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;


    public HomeController(UserService userService, SongService songService, CurrentUser currentUser, ModelMapper modelMapper) {
        this.userService = userService;
        this.songService = songService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String index(Model model){
        if(!this.userService.userIsLoggedIn()){
            return "index";
        }

        List<SongViewModel> songs = this.songService.getAllSongs();
        model.addAttribute("songs", songs);
        UserEntity user = this.userService.findById(currentUser.getId());
        List<SongViewModel> userSongs = user.getSongs()
                .stream()
                .map(songEntity -> this.modelMapper.map(songEntity, SongViewModel.class))
                .collect(Collectors.toList());

        model.addAttribute("userSongs", userSongs);

        int totalAmountOfTime = user.getSongs().stream()
                .map(SongEntity::getDuration)
                .reduce(Integer::sum).orElse(0);

        int min = (totalAmountOfTime / 60) % 60;
        int sec = totalAmountOfTime % 60;

        String amountOfTime = String.format("%d:%02d", min, sec);
        model.addAttribute("amountOfTime", amountOfTime);
        return "home";
    }

}
