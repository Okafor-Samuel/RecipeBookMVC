package com.example.bookrecipe.Controller;

import com.example.bookrecipe.Model.Entity.User;
import com.example.bookrecipe.Model.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CreateAccountController {

//    @Autowired
//    private EmailService emailService;
    @Autowired
    private UserService userService;

    @PostMapping("/create-account")
    public String createAccount(
            @RequestParam("username") String username,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword, Model model
            ) {

        if(password.equals(confirmPassword)) {
            User user = new User(username, firstName, lastName, password, email);
            userService.saveUser(user);

            //E-mail authentication

//            String subject = "Account Confirmation";
//            String text = "Thank you for creating an account. Please click on the following link to confirm your email: http://localhost:8080/confirm-email?email=" + user.getUserEmail();
//            emailService.sendSimpleMessage(user.getUserEmail(), subject, text);

            model.addAttribute("accountSuccessfullyCreated", "You have succesfully created an account.");
            return "../templates/html/createAccount";
        }else {
            model.addAttribute("errorMessage", "Passwords do not match");
            return "../templates/html/createAccount";
        }

    }

}
