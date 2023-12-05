package project.week0project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.week0project.models.Cocktail;
import project.week0project.services.CocktailService;

import java.util.List;

@Controller
public class MainController {
    private final CocktailService cocktailService;
    @Autowired
    public MainController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }
@GetMapping("/")
public String index(Model model){
        List<Cocktail> allCocktails = cocktailService.findAllAndSortByName();
        model.addAttribute("allCocktails", allCocktails);
        return "index";
}

    @GetMapping("/add")
    public String getRandomCocktail() {
        cocktailService.getRandomCocktail();
        return "redirect:/";
    }

    @GetMapping("/alcoholic")
    public String alcoholic(Model model){
        List<Cocktail> allCocktails = cocktailService.getAlcoholic();
        model.addAttribute("allCocktails", allCocktails);
        return "index";
    }

    @GetMapping("/nonalcoholic")
    public String nonalcoholic(Model model){
        List<Cocktail> allCocktails = cocktailService.getNonAlcoholic();
        model.addAttribute("allCocktails", allCocktails);
        return "index";
    }

    @GetMapping("/random")
    public String random(Model model){
        List<Cocktail> allCocktails = cocktailService.findRandom();
        model.addAttribute("allCocktails", allCocktails);
        return "index";
    }
}
