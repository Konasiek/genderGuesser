package com.example.genderguesser.controllers;

import com.example.genderguesser.models.Gender;
import com.example.genderguesser.models.GenderResponse;
import com.example.genderguesser.models.GuessVariant;
import com.example.genderguesser.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/")
public class GenderController {

    private GenderService genderService;

    @Autowired
    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping(value = {"/guess-gender/{name}/{guessVariant}", "/guess-gender/{name}"}, produces = {"application/hal+json"})
    public ResponseEntity<EntityModel<GenderResponse>> guessGender(
            @PathVariable("name") String name,
            @PathVariable(value = "guessVariant", required = false) GuessVariant guessVariant) {

        try {
            Gender guessedGender;
            if (guessVariant == null || guessVariant.equals(GuessVariant.SINGLE)) {
                guessVariant = GuessVariant.SINGLE;
                guessedGender = genderService.checkSingleName(name);
            } else if (guessVariant.equals(GuessVariant.MULTIPLE)) {
                guessedGender = genderService.checkMultipleName(name);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            GenderResponse genderResponse = new GenderResponse(name, guessedGender, guessVariant);
            Link link = linkTo(methodOn(GenderController.class).guessGender(name, guessVariant)).withSelfRel();
            EntityModel guessGenderResponseEntityModel = EntityModel.of(genderResponse, link);

            return new ResponseEntity<EntityModel<GenderResponse>>(guessGenderResponseEntityModel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-tokens")
    public ResponseEntity<List<String>> getTokens(
            @RequestParam Gender gender,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size) {

        try {
            Pageable paging = PageRequest.of(page, size);
            List<String> response;

            if (gender.equals(Gender.MALE) || gender.equals(Gender.FEMALE)) {
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
