package com.example.bookrecipe.Controller;

import com.example.bookrecipe.Model.Entity.Recipe;
import com.example.bookrecipe.Model.Entity.User;
import com.example.bookrecipe.Model.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;


@Controller
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @GetMapping("/myProfile")
    public String myProfile(Model model, HttpSession session) {
        // retrieve the logged-in user's information from the session
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        addLoggedInUser(model, session);
        List<Recipe> favouriteRecipes = loggedInUser.getFavouriteRecipe();

        // convert the userProfileImg bytes to a base64 encoded string
        byte[] userProfileImg = loggedInUser.getUserProfileImg();
        String userProfileImgBase64 = userProfileImg != null ? Base64.getEncoder().encodeToString(userProfileImg) : null;

        // add the user's information and the image URL to the model
        model.addAttribute("favouriteRecipes", favouriteRecipes);
        model.addAttribute("userProfileImageUrl", userProfileImgBase64 != null ? "data:image/png;base64," + userProfileImgBase64 : null);

        // return the view for the profile page
        return "../templates/html/myProfile";
    }

    @PostMapping("/myProfile/update")
    public String updateMyProfile(@RequestParam("profile-pic") MultipartFile profilePic,
                                  @RequestParam("facebook") String facebook,
                                  @RequestParam("instagram") String instagram,
                                  @RequestParam("tikTok") String tikTok,
                                  HttpSession session) throws IOException {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/login";
        }

        if (!profilePic.isEmpty()) {
            loggedInUser.setUserProfileImg(profilePic.getBytes());
        }

        if (!facebook.isEmpty()) {
            loggedInUser.setUserFacebook(facebook);
        }

        if (!instagram.isEmpty()) {
            loggedInUser.setUserInstagram(instagram);
        }

        if (!tikTok.isEmpty()) {
            loggedInUser.setUserTikTok(tikTok);
        }

        userService.saveUser(loggedInUser);
        return "redirect:/myProfile";
    }
}
