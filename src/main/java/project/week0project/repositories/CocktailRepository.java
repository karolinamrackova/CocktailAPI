package project.week0project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.week0project.models.Cocktail;

public interface CocktailRepository extends JpaRepository<Cocktail, Integer> {
}
