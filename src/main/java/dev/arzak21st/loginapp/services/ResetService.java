package dev.arzak21st.loginapp.services;

import dev.arzak21st.loginapp.models.User;
import dev.arzak21st.loginapp.repositories.UpdateRepository;
import dev.arzak21st.loginapp.utilities.ApplicationContextUtility;
import static dev.arzak21st.loginapp.utilities.ValidateUtility.validateParameters;
import java.sql.Date;

public class ResetService extends ApplicationContextUtility {

    UpdateRepository updateRepository = (UpdateRepository) getApplicationContext().getBean("updateRepository");

    public boolean resetUser(String firstName, String lastName, Date dateOfBirth, String gender, String country, Integer userId) {

        boolean userIsReset = false;

        Object[] parameters = {firstName, lastName, dateOfBirth, gender, country, userId};
        boolean parametersAreValid = validateParameters(parameters);

        if(parametersAreValid) {

            User user = new User(userId, firstName, lastName, dateOfBirth, gender, country);
            userIsReset = updateRepository.updateUser(user);
        }
        return userIsReset;
    }
}
