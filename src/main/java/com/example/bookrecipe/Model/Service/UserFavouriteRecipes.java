package com.example.bookrecipe.Model.Service;

import com.example.bookrecipe.Model.Entity.Recipe;
import com.example.bookrecipe.Model.Entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "userfavouriterecipes")
public class UserFavouriteRecipes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public UserFavouriteRecipes() {
    }

    public UserFavouriteRecipes(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
    }

    public Long getFavId() {
        return favId;
    }

    public void setFavId(Long favId) {
        this.favId = favId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}