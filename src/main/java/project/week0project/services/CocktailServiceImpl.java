package project.week0project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.week0project.repositories.CocktailRepository;

@Service
public class CocktailServiceImpl implements CocktailService{
    private final CocktailRepository cocktailRepository;
    @Autowired
    public CocktailServiceImpl(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }
}
