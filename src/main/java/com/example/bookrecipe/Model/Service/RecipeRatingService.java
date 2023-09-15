package com.example.bookrecipe.Model.Service;

import com.example.bookrecipe.Model.Entity.Recipe;
import com.example.bookrecipe.Model.Entity.RecipeRating;
import com.example.bookrecipe.Model.Entity.User;
import com.example.bookrecipe.Model.Repository.RecipeRatingRepository;
import com.example.bookrecipe.Model.Repository.RecipeRepository;
import com.example.bookrecipe.Model.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeRatingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeRatingRepository recipeRatingRepository;

    public void addRating(Long userId, Long recipeId, int rating) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new IllegalArgumentException("Recipe not found"));

        // Check if a rating already exists and update it or create a new one
        RecipeRating existingRating = recipeRatingRepository.findByUserAndRecipe(user, recipe);
        if (existingRating != null) {
            existingRating.setRating(rating);
            recipeRatingRepository.save(existingRating); // Save the updated rating to the database
        } else {
            RecipeRating newRating = new RecipeRating();
            newRating.setUser(user);
            newRating.setRecipe(recipe);
            newRating.setRating(rating);
            recipeRatingRepository.save(newRating);
        }
    }



    public double getAverageRating(Long recipeId) {
        List<RecipeRating> ratings = recipeRatingRepository.findByRecipeRecipeId(recipeId);
        if (ratings.isEmpty()) {
            return 0;
        }
        int totalRatings = ratings.stream().mapToInt(RecipeRating::getRating).sum();
        int numberOfRatings = ratings.size();
        double averageRating = (double) totalRatings / numberOfRatings;
        return Math.round(averageRating * 10) / 10.0; // Round to one decimal place
    }


    public Double getAverageRatingByRecipeId(Long recipeId) {
        List<RecipeRating> ratings = recipeRatingRepository.findByRecipeRecipeId(recipeId);
        if (ratings.isEmpty()) {
            return 0.0;
        }
        int totalRatings = ratings.stream().mapToInt(RecipeRating::getRating).sum();
        int numberOfRatings = ratings.size();
        return (double) totalRatings / numberOfRatings;
    }


}

