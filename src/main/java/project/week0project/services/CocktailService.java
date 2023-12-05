package project.week0project.services;

import project.week0project.dto.CocktailDTO;
import project.week0project.models.Cocktail;

import java.util.List;

public interface CocktailService {
    List<Cocktail> getRandomCocktail();
    void saveCocktailsToDatabase(List<Cocktail> list);

    List<Cocktail> getNonAlcoholic();
    List<Cocktail> getAlcoholic();
}
