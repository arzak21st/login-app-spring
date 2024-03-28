package dev.arzak21st.loginapp.services;

import dev.arzak21st.loginapp.repositories.RemoveRepository;
import dev.arzak21st.loginapp.utilities.ApplicationContextUtility;
import static dev.arzak21st.loginapp.utilities.ValidateUtility.validateParameters;

public class RemoveService extends ApplicationContextUtility {

    RemoveRepository removeRepository = (RemoveRepository) getApplicationContext().getBean("removeRepository");

    public boolean removeUserById(Integer userId) {

        boolean userIsRemoved = false;

        Object[] parameters = {userId};
        boolean parametersAreValid = validateParameters(parameters);

        if(parametersAreValid) {
            userIsRemoved = removeRepository.removeUserById(userId);
        }
        return userIsRemoved;
    }
}
