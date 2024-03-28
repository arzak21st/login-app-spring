package dev.arzak21st.loginapp.controllers;

import dev.arzak21st.loginapp.models.UserCredential;
import dev.arzak21st.loginapp.services.ResetPasswordService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResetPasswordController {

    ResetPasswordService resetPasswordService = new ResetPasswordService();

    @GetMapping("/resetPassword")
    public String resetPasswordGet() {
        return "auth-reset-password";
    }

    @PostMapping("/resetPassword")
    public String resetPasswordPost(HttpServletRequest request) {

        String view = "auth-reset-password";

        // Get parameters
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newPasswordConfirmation = request.getParameter("newPasswordConfirmation");

        // Compare passwords
        if(!newPassword.equals(newPasswordConfirmation)) { // Password mismatch

            // Back to reset password page
            String failMessage = "Reset failed: Passwords missmatch!";
            request.setAttribute("failMessage", failMessage);
        }
        else { // Password match

            // Reset password proccess
            // Get user id
            HttpSession session = request.getSession();
            UserCredential userCredential = (UserCredential) session.getAttribute("userCredential");
            Integer userId = userCredential.getUser().getUserId();

            // Check if old password is correct
            System.out.println("\n checkOldPassword()");
            boolean oldPasswordIsCorrect = resetPasswordService.checkOldPassword(userId, oldPassword);

            if(oldPasswordIsCorrect) {

                // Reset password
                System.out.println("\n resetPassword()");
                boolean passwordIsReset = resetPasswordService.resetPassword(newPassword, userId);

                if(passwordIsReset) {

                    // Go back to reset password page
                    String successMessage = "Password Reset Succeeded: <a href=\"login\"><b>Login</b></a>?";
                    request.setAttribute("successMessage", successMessage);
                }
            }
            else {

                // Back to reset password page
                String failMessage = "Password Reset failed: Old password is not correct!";
                request.setAttribute("failMessage", failMessage);
            }
        }
        return view;
    }
}
