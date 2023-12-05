package project.week0project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.week0project.api.CocktailApiService;
import project.week0project.dto.CocktailDTO;
import project.week0project.models.Cocktail;
import project.week0project.repositories.CocktailRepository;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CocktailServiceImpl implements CocktailService {
    private final CocktailRepository cocktailRepository;
    private final CocktailApiService cocktailApiService;

    @Autowired
    public CocktailServiceImpl(CocktailRepository cocktailRepository, CocktailApiService cocktailApiService) {
        this.cocktailRepository = cocktailRepository;
        this.cocktailApiService = cocktailApiService;
    }

    public List<Cocktail> getRandomCocktail() {
        try {
            // Make a call to the Retrofit service
            Call<CocktailDTO> call = cocktailApiService.getRandomCocktail();
            Response<CocktailDTO> response = call.execute();

            if (response.isSuccessful()) {
                // If the response is successful, save the data to the database
                CocktailDTO cocktailDTO = response.body();
                List<Cocktail> cocktailList = dtoToList(cocktailDTO);
                saveCocktailsToDatabase(cocktailList);
                return cocktailList;
            } else {
                // Handle error (e.g., log, throw an exception)
                return null;
            }
        } catch (Exception e) {
            // Handle exception
            return null;
        }
    }

    public List<Cocktail> dtoToList (CocktailDTO cocktailDTO){
        List<Cocktail> listOfCocktails = new ArrayList<>();
        List<CocktailDTO.Drink> drinks = cocktailDTO.getDrinks();
        for (CocktailDTO.Drink drink : drinks) {
            Cocktail cocktail = new Cocktail();
            cocktail.setName(drink.getStrDrink());
            cocktail.setAlcoholic(drink.getStrAlcoholic());
            cocktail.setInstructions(drink.getStrInstructions());
            listOfCocktails.add(cocktail);
        }
        return listOfCocktails;
    }
    public void saveCocktailsToDatabase(List<Cocktail> list) {
        cocktailRepository.saveAll(list);
    }

    @Override
    public List<Cocktail> getNonAlcoholic() {
        return cocktailRepository.findCocktailByAlcoholicEqualsIgnoreCase("non alcoholic");
    }
    @Override
    public List<Cocktail> getAlcoholic() {
        return cocktailRepository.findCocktailByAlcoholicEqualsIgnoreCase("alcoholic");
    }

    @Override
    public List<Cocktail> findAll() {
        Sort sort = Sort.by(Sort.Order.asc("name"));
        return cocktailRepository.findAll(sort);
    }

    @Override
    public List<Cocktail> findRandom() {
        List<Cocktail> list = new ArrayList<>();
        Random random = new Random();

        do {
            int index = random.nextInt((int) cocktailRepository.count());
            Cocktail cocktail = cocktailRepository.findCocktailById(index);

            if (cocktail != null) {
                list.add(cocktail);
                break;
            }
        } while (true);

        return list;
    }

}
