package com.example.bookrecipe.Model.Repository;


import com.example.bookrecipe.Model.Entity.Recipe;
import com.example.bookrecipe.Model.Entity.RecipeRating;
import com.example.bookrecipe.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRatingRepository extends JpaRepository<RecipeRating, Long> {
    List<RecipeRating> findByRecipeRecipeId(Long recipeId);

    RecipeRating findByUserAndRecipe(User user, Recipe recipe);

}

