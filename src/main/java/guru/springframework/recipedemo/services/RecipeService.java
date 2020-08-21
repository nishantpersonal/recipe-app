package guru.springframework.recipedemo.services;

import guru.springframework.recipedemo.commands.RecipeCommand;
import guru.springframework.recipedemo.domain.Recipe;

import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteRecipeById(Long id);
}
