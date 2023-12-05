package project.week0project.services;

import project.week0project.dto.CocktailDTO;

public interface CocktailService {
    CocktailDTO getRandomCocktail();
    void saveCocktailToDatabase(CocktailDTO cocktailDTO);
}
