package com.example.bookrecipe.Model.Service;

import com.example.bookrecipe.Model.Entity.Recipe;
import com.example.bookrecipe.Model.Entity.User;
import com.example.bookrecipe.Model.Repository.RecipeRepository;
import com.example.bookrecipe.Model.Repository.UserFavouriteRecipesRepository;
import com.example.bookrecipe.Model.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserFavouriteService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserFavouriteRecipesRepository userFavouriteRecipesRepository;

    public void saveFavouriteRecipe(Long userId, Long recipeId) {

        User user = userRepository.findById(userId).orElse(null);
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);

        if (user != null && recipe != null) {
            List<Recipe> favoriteRecipes = user.getFavouriteRecipe();

            if (favoriteRecipes.contains(recipe)) {
                favoriteRecipes.remove(recipe);
            } else {
                favoriteRecipes.add(recipe);
            }
            userRepository.save(user);
        }
    }

    public List<Long> getFavouriteRecipeIds(Long userId) {
        List<UserFavouriteRecipes> userFavouriteRecipes = userFavouriteRecipesRepository.findByUserUserId(userId);
        List<Long> favouriteRecipeIds = new ArrayList<>();

        for (UserFavouriteRecipes userFavouriteRecipe : userFavouriteRecipes) {
            favouriteRecipeIds.add(userFavouriteRecipe.getRecipe().getRecipeId());
        }

        return favouriteRecipeIds;
    }
}
