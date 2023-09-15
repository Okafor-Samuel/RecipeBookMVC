package com.example.bookrecipe.Controller;

import com.example.bookrecipe.Model.Entity.User;
import com.example.bookrecipe.Model.Repository.UserFavouriteRecipesRepository;
import com.example.bookrecipe.Model.Service.UserFavouriteRecipes;
import com.example.bookrecipe.Model.Service.UserFavouriteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class FavouritesController extends BaseController {

    @Autowired
    private UserFavouriteService userFavouriteService;

    @Autowired
    private UserFavouriteRecipesRepository userFavouriteRecipesRepository;

//        This is for favourites in users myFavourites
//    @PostMapping("/favourites/add")
//    public String addToFavorites(@RequestParam("recipeId") Long recipeId, HttpSession session) {
//        // Retrieve the recipe ID from the request body
//        if (session == null || session.getAttribute("loggedInUser") == null) {
//            return "index";
//        } else {
//            userFavouriteService.saveFavouriteRecipe(((User) session.getAttribute("loggedInUser")).getUserId(), recipeId);
//            return "redirect:/myFavourites";
//        }
//    }

    @PostMapping("/favourites/add")
    @ResponseBody
    public Map<String, Object> addToFavorites(@RequestParam("recipeId") Long recipeId, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.put("success", false);
            response.put("message", "User not logged in");
        } else {
            userFavouriteService.saveFavouriteRecipe(((User) session.getAttribute("loggedInUser")).getUserId(), recipeId);
            response.put("success", true);
        }
        return response;
    }



//    This is for search results favourites

//    @PostMapping("/searchFavourites")
//    public String addToFavouritesSearchResults(@RequestParam("recipeId") Long recipeId,
//                                               @PathVariable String searchTerm,
//                                               HttpSession session) {
//        if (session == null || session.getAttribute("loggedInUser") == null) {
//            return "index";
//        } else {
//            userFavouriteService.saveFavouriteRecipe(((User) session.getAttribute("loggedInUser")).getUserId(), recipeId);
//            return "redirect:/search?searchTerm=" + searchTerm;
//        }
//    }

//    @PostMapping("/favouritesSearch")
//    public String addToFavouritesSearchResults(@RequestParam("recipeId") Long recipeId, HttpSession session) {
//        if (session == null || session.getAttribute("loggedInUser") == null) {
//            // Säg åt användare att skapa konto eller logga in
//            return "index";
//        } else {
//            userFavouriteService.saveFavouriteRecipe(((User) session.getAttribute("loggedInUser")).getUserId(), recipeId);
//            return
//        }
//    }

    @GetMapping("/myFavourites")
    public String myFavourites(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        List<UserFavouriteRecipes> favouriteRecipes = userFavouriteRecipesRepository.findByUserUserId(loggedInUser.getUserId());
        model.addAttribute("favouriteRecipes", favouriteRecipes);
        addLoggedInUser(model, session);
        return "../templates/html/myFavourites";
    }
}


