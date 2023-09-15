package com.example.bookrecipe.Controller;

import com.example.bookrecipe.Model.Entity.User;
import com.example.bookrecipe.Model.Service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/addComment")
    public String addComment(HttpSession session, @RequestParam("recipeId") Long recipeId, @RequestParam("comment") String content) {
        User user = (User) session.getAttribute("loggedInUser");

        if(user == null) {
            throw new RuntimeException("User not found");
        }else{
            commentService.addComment(user, recipeId, content);
        }
        return "redirect:/recipe/" + recipeId;
    }
}
