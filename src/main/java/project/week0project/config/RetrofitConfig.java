package project.week0project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.week0project.api.CocktailApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {

    @Bean
    public CocktailApiService cocktailApiService() {
        return new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CocktailApiService.class);
    }
}
