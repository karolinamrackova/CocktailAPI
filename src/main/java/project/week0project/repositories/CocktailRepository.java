package project.week0project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.week0project.models.Cocktail;

import java.util.List;

public interface CocktailRepository extends JpaRepository<Cocktail, Integer> {
    List<Cocktail> findCocktailByAlcoholicEqualsIgnoreCase(String alcoholic);
    Cocktail findCocktailById(Integer id);
}
