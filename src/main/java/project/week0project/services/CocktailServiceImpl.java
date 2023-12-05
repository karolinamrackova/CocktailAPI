package project.week0project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.week0project.api.CocktailApiService;
import project.week0project.dto.CocktailDTO;
import project.week0project.models.Cocktail;
import project.week0project.repositories.CocktailRepository;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

@Service
public class CocktailServiceImpl implements CocktailService {
    private final CocktailRepository cocktailRepository;
    private final CocktailApiService cocktailApiService;

    @Autowired
    public CocktailServiceImpl(CocktailRepository cocktailRepository, CocktailApiService cocktailApiService) {
        this.cocktailRepository = cocktailRepository;
        this.cocktailApiService = cocktailApiService;
    }

    public CocktailDTO getRandomCocktail() {
        try {
            // Make a call to the Retrofit service
            Call<CocktailDTO> call = cocktailApiService.getRandomCocktail();
            Response<CocktailDTO> response = call.execute();

            if (response.isSuccessful()) {
                // If the response is successful, save the data to the database
                CocktailDTO cocktailDTO = response.body();
                saveCocktailToDatabase(cocktailDTO);
                return cocktailDTO;
            } else {
                // Handle error (e.g., log, throw an exception)
                return null;
            }
        } catch (Exception e) {
            // Handle exception
            return null;
        }
    }

    public void saveCocktailToDatabase(CocktailDTO cocktailDTO) {
        List<CocktailDTO.Drink> drinks = cocktailDTO.getDrinks();

        for (CocktailDTO.Drink drink : drinks) {
            Cocktail cocktail = new Cocktail();
            cocktail.setName(drink.getStrDrink());
            cocktail.setAlcoholic(drink.getStrAlcoholic());
            cocktail.setInstructions(drink.getStrInstructions());
            cocktailRepository.save(cocktail);
        }
    }


}
