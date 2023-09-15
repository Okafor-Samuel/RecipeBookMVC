package com.example.bookrecipe.Model.Service;

import com.example.bookrecipe.Model.Entity.Recipe;
import com.example.bookrecipe.Model.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

//    public Recipe getRecipeById(Long recipeId) {
//        return recipeRepository.findById(recipeId).orElseThrow(() -> new IllegalArgumentException("Recipe not found"));
//    }

    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId).orElse(null);
    }


    public void saveRecipe(Recipe recipe) {
        if (recipeRepository.findByRecipeName(recipe.getRecipeName()).isEmpty()) {
            recipeRepository.save(recipe);
        }
    }



}
