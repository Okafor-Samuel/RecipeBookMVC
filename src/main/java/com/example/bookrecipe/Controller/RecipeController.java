package com.example.bookrecipe.Controller;


import com.example.bookrecipe.Model.Entity.Comment;
import com.example.bookrecipe.Model.Entity.Recipe;
import com.example.bookrecipe.Model.Repository.RecipeRepository;
import com.example.bookrecipe.Model.Service.CommentService;
import com.example.bookrecipe.Model.Service.RecipeRatingService;
import com.example.bookrecipe.Model.Service.RecipeService;
import com.example.bookrecipe.Model.TastyApi;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


@Controller
public class RecipeController extends BaseController{

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeRatingService recipeRatingService;
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CommentService commentService;


    @GetMapping("/recipe/{id}")
    public String getRecipe(@PathVariable Long id, Model model, HttpSession session) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            model.addAttribute("recipe", recipe.get());
            addLoggedInUser(model,session);
            List<Comment> comments = commentService.getCommentsByRecipeId(id);
            model.addAttribute("comments", comments);


            model.addAttribute("averageRating", recipeRatingService.getAverageRating(recipe.get().getRecipeId()));


            return "../templates/html/recipe";
        } else {
            model.addAttribute("errorMessage", "Recipe not found");
            return "../templates/html/error";
        }
    }



    @GetMapping("/createAccount")
    public String getCreateAccountPage() {
//        return "../templates/html/createAccount";
        return "../templates/html/createAccount";
    }

    @GetMapping("/fetch-recipe")
    public void fetchRecipe() throws Exception {
        TastyApi tastyApi = new TastyApi(recipeRepository);
        tastyApi.fetchRecipes();
    }

    @GetMapping("/clear-database")
    public void clearRecipeTable() {
        recipeRepository.deleteAll();
    }

//    @GetMapping("/delete-duplicates")
//    public void deleteDuplicateRecipes() {
//        List<String> distinctRecipeNames = recipeRepository.findDistinctRecipeNames();
//        for (String recipeName : distinctRecipeNames) {
//            List<Recipe> recipesToDelete = recipeRepository.findByRecipeName(recipeName);
//            if (recipesToDelete.size() > 1) {
//                recipeRepository.deleteInBatch(recipesToDelete.subList(1, recipesToDelete.size()));
//            }
//        }
//    }
}




