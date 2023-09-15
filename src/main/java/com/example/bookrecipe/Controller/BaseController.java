package com.example.bookrecipe.Controller;

import com.example.bookrecipe.Model.Entity.User;
import com.example.bookrecipe.Model.Utils.ThymeleafUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
public abstract class BaseController {

    protected void addLoggedInUser(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") != null) {
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            model.addAttribute("loggedInUser", loggedInUser);
        }
        model.addAttribute("thymeleafUtil", new ThymeleafUtil());
    }
}