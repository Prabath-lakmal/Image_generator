package com.spring_AI.Image_generator.controller;

import com.spring_AI.Image_generator.service.ImageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService){
        this.imageService = imageService;
    }

//    Image Generate
    @PostMapping
    public String generate(@RequestParam("prompt") String prompt) throws IOException {

        return imageService.generateImage(prompt);
    }


}
