package project.week0project.api;

import project.week0project.dto.CocktailDTO;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CocktailApiService {
    @GET("random.php")
    Call<CocktailDTO> getRandomCocktail();
}

