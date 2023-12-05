package project.week0project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.week0project.dto.CocktailDTO;
import project.week0project.models.Cocktail;
import project.week0project.services.CocktailService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainRestController {
    private final CocktailService cocktailService;
    @Autowired
    public MainRestController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }
    @GetMapping("/random")
    public List<Cocktail> getRandomCocktail() {
        return cocktailService.getRandomCocktail();
    }

    @GetMapping("/nonalcoholic")
    public List<Cocktail> getNonAlcoholic(){
        return cocktailService.getNonAlcoholic();
    }

    @GetMapping("/alcoholic")
    public List<Cocktail> getAlcoholic(){
        return cocktailService.getAlcoholic();
    }
}
