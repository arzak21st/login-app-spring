package dev.arzak21st.loginapp.controllers;

import dev.arzak21st.loginapp.models.UserCredential;
import dev.arzak21st.loginapp.services.RemoveService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RemoveController {

    RemoveService removeService = new RemoveService();

    @GetMapping("/remove")
    public String removeGet(HttpServletRequest request) {
        return removePost(request);
    }

    @PostMapping("/remove")
    public String removePost(HttpServletRequest request) {

        String view = "auth-login";

        // Check if user exists
        HttpSession session = request.getSession();
        UserCredential userCredential = (UserCredential) session.getAttribute("userCredential");

        if(userCredential != null) { // User is logged in

            // Remove proccess
            System.out.println("\n removeUserById()");
            Integer userId = userCredential.getUser().getUserId();
            removeService.removeUserById(userId);

            // Close session and go to login page
            request.getSession().invalidate();
        }
        return view;
    }
}
