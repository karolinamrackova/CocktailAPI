package project.week0project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import project.week0project.services.CocktailService;

@RestController
public class MainRestController {
    private final CocktailService cocktailService;
    @Autowired
    public MainRestController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

}
