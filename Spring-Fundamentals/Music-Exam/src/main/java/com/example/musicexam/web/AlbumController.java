package com.example.musicexam.web;

import com.example.musicexam.model.binding.AlbumBindingModel;
import com.example.musicexam.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumService albumService;
    private final ModelMapper modelMapper;

    public AlbumController(AlbumService albumService, ModelMapper modelMapper) {
        this.albumService = albumService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String add(){
        return "add-album";
    }

    @PostMapping("/add")
    public String addPost(@Valid AlbumBindingModel albumBindingModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("albumBindingModel", albumBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.albumBindingModel", bindingResult);

            return "redirect:add";
        }

        //todo: add album in db

        return "redirect:/";
    }

    @ModelAttribute
    public AlbumBindingModel albumBindingModel(){
        return new AlbumBindingModel();
    }
}
