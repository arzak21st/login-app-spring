package dev.arzak21st.loginapp.controllers;

import dev.arzak21st.loginapp.models.UserCredential;
import dev.arzak21st.loginapp.services.RegisterService;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    RegisterService registerService = new RegisterService();

    @GetMapping("/register")
    public String registerGet(HttpServletRequest request) {

        String view = "auth-register";

        // Close session if exsists
        HttpSession session = request.getSession();
        if(session != null) { // Session exists
            session.invalidate();
        }
        return view;
    }

    @PostMapping("/register")
    public String registerPost(HttpServletRequest request) {

        String view = "auth-register";

        // Get parameters
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        Date dateOfBirth = Date.valueOf(request.getParameter("dateOfBirth"));
        String country = request.getParameter("country");

        String gender = request.getParameter("gender");

        // Compare passwords
        if(!password.equals(passwordConfirmation)) { // Passwords mismatch

            // Back to register page
            String failMessage = "Register failed: Passwords missmatch!";
            request.setAttribute("failMessage", failMessage);
        }
        else { // Passwords match

            // Register proccess
            // Check if user credentials (username & email) were already used
            System.out.println("\n checkUserCredential()");
            UserCredential userCredential = registerService.checkUserCredential(username, email);

            if(userCredential == null) { // User credentials were not already used

                // Register the user
                System.out.println("\n registerUser()");
                boolean userIsRegistered = registerService.registerUser(firstName, lastName, dateOfBirth, gender, country);

                if(userIsRegistered) {

                    // Register user credentials
                    System.out.println("\n getRegisteredUserId()");
                    Integer registeredUserId = registerService.getRegisteredUserId();

                    System.out.println("\n registerUserCredential()");
                    boolean userCredentialIsRegistered = registerService.registerUserCredential(registeredUserId, username, email, password);

                    if(userCredentialIsRegistered) {

                        // Go back to register page
                        String successMessage = "Register succeeded: <a href=\"login\"><b>Login</b></a>?";
                        request.setAttribute("successMessage", successMessage);
                    }
                }
            }
            else { // User credentials were already used

                // Back to register page
                String failMessage = "Register failed: Username or Email were already used!";
                request.setAttribute("failMessage", failMessage);
            }
        }
        return view;
    }
}
