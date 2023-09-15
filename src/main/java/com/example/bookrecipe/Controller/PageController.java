package com.example.bookrecipe.Controller;

import com.example.bookrecipe.Model.Entity.Recipe;
import com.example.bookrecipe.Model.Entity.User;
import com.example.bookrecipe.Model.Repository.RecipeRepository;
import com.example.bookrecipe.Model.Service.RecipeRatingService;
import com.example.bookrecipe.Model.Service.UserFavouriteService;
import com.example.bookrecipe.Model.Service.UserService;
import com.example.bookrecipe.Model.Utils.ThymeleafUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class PageController extends BaseController{

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserFavouriteService userFavouriteService;

    @Autowired
    private RecipeRatingService recipeRatingService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        // popular recipes in index
        List<Long> popularRecipeIds = Arrays.asList(484L, 1559L, 4036L, 4288L);
        List<Recipe> popularRecipes = (List<Recipe>) recipeRepository.findAllById(popularRecipeIds);
        model.addAttribute("popularRecipes", popularRecipes);

        // latest recipes in index
        List<Long> latestRecipeIds = Arrays.asList(6977L, 6978L, 6979L, 6980L);
        List<Recipe> latestRecipes = (List<Recipe>) recipeRepository.findAllById(latestRecipeIds);
        model.addAttribute("latestRecipes", latestRecipes);

        Map<Long, Double> averageRatings = new HashMap<>();
        for (Recipe recipe : Stream.concat(popularRecipes.stream(), latestRecipes.stream()).distinct().collect(Collectors.toList())) {
            averageRatings.put(recipe.getRecipeId(), recipeRatingService.getAverageRatingByRecipeId(recipe.getRecipeId()));
        }
        model.addAttribute("averageRatings", averageRatings);

        // Initialize an empty list of favoriteRecipeIds
        List<Long> favoriteRecipeIds = new ArrayList<>();

        // Add new ThymeleafUtil instance to the model
        model.addAttribute("thymeleafUtil", new ThymeleafUtil());
        if (session.getAttribute("loggedInUser") != null) {
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            model.addAttribute("loggedInUser", loggedInUser);
            favoriteRecipeIds = userFavouriteService.getFavouriteRecipeIds(loggedInUser.getUserId());
        }

        model.addAttribute("favoriteRecipeIds", favoriteRecipeIds);
        return "../templates/index";
    }

    @GetMapping("/articles")
    public String articles(Model model, HttpSession session) {
        addLoggedInUser(model, session);
        return "../templates/html/articles";
    }

    @GetMapping("/about")
    public String aboutUs(Model model, HttpSession session) {
        addLoggedInUser(model, session);
        return "../templates/html/about";
    }

    @GetMapping("/contact")
    public String contactUs(Model model, HttpSession session) {
        addLoggedInUser(model, session);
        return "../templates/html/contact";
    }

    @GetMapping("/settings")
    public String settings(Model model, HttpSession session) {
        addLoggedInUser(model, session);
        return "../templates/html/settings";
    }

    @PostMapping("/settings/update")
    public String settingsUpdate(@RequestParam("name") String name,
                                 @RequestParam("password") String password,
                                 @RequestParam("confirm_password") String confirmPassword,
                                 HttpSession session, Model model) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        // Update username
        if (name != null && !name.trim().isEmpty()) {
            loggedInUser.setUsername(name.trim());
        }

        // Update password
        if (password != null && !password.trim().isEmpty()) {
            if (password.equals(confirmPassword)) {
                // You might want to hash the password before saving it
                loggedInUser.setUserPassword(passwordEncoder.encode(password));
            } else {
                model.addAttribute("error", "Passwords do not match.");
                return "../templates/html/settings";
            }
        }

        userService.saveUser(loggedInUser);
        return "redirect:/settings";
    }
}