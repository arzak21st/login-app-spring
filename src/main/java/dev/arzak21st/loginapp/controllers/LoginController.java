package dev.arzak21st.loginapp.controllers;

import dev.arzak21st.loginapp.models.UserCredential;
import dev.arzak21st.loginapp.services.LoginService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    LoginService loginService = new LoginService();

    @GetMapping(value = {"/login", "/logout"})
    public String logoutGet(HttpServletRequest request) {

        String view = "auth-login";

        // Close session if exsists
        HttpSession session = request.getSession();
        if(session != null) { // Session exists
            session.invalidate();
        }
        return view;
    }

    @PostMapping("/login")
    public String loginPost(HttpServletRequest request) {

        String view = "auth-login";

        // Get parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Login proccess
        System.out.println("\n loginUser()");
        UserCredential userCredential = loginService.loginUser(email, password);

        if(userCredential != null) { // User is logged in

            // Open session and go to main page
            HttpSession session = request.getSession();
            session.setAttribute("userCredential", userCredential);
            view = "index";
        }
        else { // User is not logged in

            // Back to login page
            String failMessage = "Login failed: Wrong email or password!";
            request.setAttribute("failMessage", failMessage);
            view = "auth-login";
        }
        return view;
    }
}
