package dev.arzak21st.loginapp.services;

import dev.arzak21st.loginapp.repositories.GetRepository;
import dev.arzak21st.loginapp.repositories.UpdateRepository;
import dev.arzak21st.loginapp.utilities.ApplicationContextUtility;
import static dev.arzak21st.loginapp.utilities.ValidateUtility.validateParameters;

public class ResetPasswordService extends ApplicationContextUtility {

    GetRepository getRepository = (GetRepository) getApplicationContext().getBean("getRepository");
    UpdateRepository updateRepository = (UpdateRepository) getApplicationContext().getBean("updateRepository");

    public boolean checkOldPassword(Integer userId, String oldPassword) {

        boolean oldPaswordIsCorrect = false;

        Object[] parameters = {userId, oldPassword};
        boolean parametersAreValid = validateParameters(parameters);

        if(parametersAreValid) {
            oldPaswordIsCorrect = getRepository.getUserCredentialByIdAndPassword(userId, oldPassword);
        }
        return oldPaswordIsCorrect;
    }

    public boolean resetPassword(String newPassword, Integer userId) {

        boolean passwordIsReset = false;

        Object[] parameters = {newPassword, userId};
        boolean parametersAreValid = validateParameters(parameters);

        if(parametersAreValid) {
            passwordIsReset = updateRepository.updatePassword(newPassword, userId);
        }
        return passwordIsReset;
    }
}
