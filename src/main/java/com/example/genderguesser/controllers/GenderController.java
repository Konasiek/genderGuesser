package com.example.genderguesser.controllers;

import com.example.genderguesser.models.GuessVariant;
import com.example.genderguesser.services.GenderService;
import io.micrometer.core.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<String> guessGender(@RequestParam("name") @NonNull String name,
                                              @RequestParam("guessVariant") String guessVariant) {

        try {
            String nameGender;
            if (guessVariant.equals(GuessVariant.single.getName())) {
                nameGender = genderService.checkSingleName(name);
            } else if (guessVariant.equals(GuessVariant.multiple.getName())) {
                nameGender = genderService.checkMultipleName(name);
            } else {
                return new ResponseEntity<>(
                        "guess variant must be " + GuessVariant.single.getName() + " or " + GuessVariant.multiple.getName(),
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(nameGender, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-tokens")
    public ResponseEntity<List<String>> getTokens(
            @RequestParam String gender,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size) {

        try {
            Pageable paging = PageRequest.of(page, size);
            List<String> response;

            if (gender.equals("male") || gender.equals("female")) {
                response = genderService.getTokens(gender, paging);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
