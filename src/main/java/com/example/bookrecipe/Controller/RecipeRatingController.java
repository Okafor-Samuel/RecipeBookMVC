package com.example.bookrecipe.Controller;


import com.example.bookrecipe.Model.Entity.User;
import com.example.bookrecipe.Model.Service.RecipeRatingService;
import com.example.bookrecipe.Model.Service.RecipeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RecipeRatingController {


    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeRatingService recipeRatingService;

    @PostMapping("/rating/add")
    @ResponseBody
    public Map<String, Object> addRating(@RequestParam("recipeId") Long recipeId, @RequestParam("rating") int rating, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.put("success", false);
            response.put("message", "User not logged in");
        } else {
            Long userId = ((User) session.getAttribute("loggedInUser")).getUserId();
            recipeRatingService.addRating(userId, recipeId, rating);
            double newAverageRating = recipeRatingService.getAverageRating(recipeId);
            response.put("success", true);
            response.put("newAverageRating", newAverageRating);
        }
        return response;
    }


    @GetMapping("/rating/average")
    @ResponseBody
    public Map<String, Object> getAverageRating(@RequestParam("recipeId") Long recipeId) {
        Map<String, Object> response = new HashMap<>();
        double averageRating = recipeRatingService.getAverageRating(recipeId);
        int roundedRating = (int) Math.round(averageRating);
        response.put("averageRating", averageRating);
        response.put("roundedRating", roundedRating);
        return response;
    }
}

