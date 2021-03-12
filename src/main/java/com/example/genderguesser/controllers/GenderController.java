package com.example.genderguesser.controllers;

import com.example.genderguesser.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class GenderController {

    private GenderService genderService;

    @Autowired
    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping("/guess-gender")
    public ResponseEntity<String> guessGender(@RequestParam String name, @RequestParam String guessVariant) {

        try {
            String nameGender;
            if (guessVariant.equals("single")) {
                nameGender = genderService.checkSingleName(name);
            } else if (guessVariant.equals("multiple")) {
                nameGender = genderService.checkMultipleName(name);
            } else {
                return new ResponseEntity<>("guess variant must be single or multiple", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(nameGender, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-tokens")
    public ResponseEntity getTokens (@RequestParam String gender) {

        try {
            if (gender.equals("male")) {
                List<String> maleTokens = genderService.getMaleTokens();
                return new ResponseEntity<>(maleTokens, HttpStatus.OK);
            } else if (gender.equals("female")) {
                List<String> femaleTokens = genderService.getFemaleTokens();
                return new ResponseEntity<>(femaleTokens, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("gender must be male or female", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
