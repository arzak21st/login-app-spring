package dev.arzak21st.loginapp.controllers;

import dev.arzak21st.loginapp.models.UserCredential;
import dev.arzak21st.loginapp.services.ResetService;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResetController {

    ResetService resetService = new ResetService();

    @GetMapping("/reset")
    public String resetGet() {
        return "features-profile";
    }

    @PostMapping("/reset")
    public String resetPost(HttpServletRequest request) {

        String view = "features-profile";

        // Get parameters
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        Date dateOfBirth = Date.valueOf(request.getParameter("dateOfBirth"));
        String country = request.getParameter("country");

        String gender = request.getParameter("gender");

        // Get user id
        HttpSession session = request.getSession();
        UserCredential userCredential = (UserCredential) session.getAttribute("userCredential");
        Integer userId = userCredential.getUser().getUserId();

        // Reset proccess
        System.out.println("\n resetUser()");
        boolean userIsReset = resetService.resetUser(firstName, lastName, dateOfBirth, gender, country, userId);

        if(userIsReset) {

            // Update session and go back to profile page
            userCredential.getUser().setFirstName(firstName);
            userCredential.getUser().setLastName(lastName);
            userCredential.getUser().setDateOfBirth(dateOfBirth);
            userCredential.getUser().setGender(gender);
            userCredential.getUser().setCountry(country);
            session.setAttribute("userCredential", userCredential);

            String successMessage = "Reset succeeded";
            request.setAttribute("successMessage", successMessage);
        }
        return view;
    }
}
