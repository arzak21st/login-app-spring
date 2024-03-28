package dev.arzak21st.loginapp.repositories;

import dev.arzak21st.loginapp.models.User;
import dev.arzak21st.loginapp.models.UserCredential;
import org.springframework.jdbc.core.JdbcTemplate;

public class SaveRepository {

    /* ========== VARIABLES ========== */
    boolean userIsSaved;
    boolean userCredentialIsSaved;

    Integer saving;

    String sqlQuery;

    Object[] sqlQueryParameters;

    JdbcTemplate jdbcTemplate;

    /* ========== CONSTRUCTORS ========== */
    public SaveRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /* ========== METHODS ========== */
    public boolean saveUser(User user) {

        userIsSaved = false;

        try {

            sqlQuery
                    = "INSERT INTO user (first_name, last_name, date_of_birth, gender, country) \n"
                    + "VALUES (?, ?, ?, ?, ?)";

            sqlQueryParameters = new Object[] {
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getGender(),
                user.getCountry(),};

            saving = jdbcTemplate.update(sqlQuery, sqlQueryParameters);

            if(saving == 1) {
                userIsSaved = true;
            }
        }
        catch(Exception exception) {
            // exception.printStackTrace();
            userIsSaved = false;
        }
        return userIsSaved;
    }

    public boolean saveUserCredential(UserCredential userCredential) {

        userCredentialIsSaved = false;

        try {

            sqlQuery
                    = "INSERT INTO user_credential (user_id, username, email, password) \n"
                    + "VALUES (?, ?, ?, ?)";

            sqlQueryParameters = new Object[] {
                userCredential.getUserId(),
                userCredential.getUsername(),
                userCredential.getEmail(),
                userCredential.getPassword(),};

            saving = jdbcTemplate.update(sqlQuery, sqlQueryParameters);

            if(saving == 1) {
                userCredentialIsSaved = true;
            }
        }
        catch(Exception exception) {
            // exception.printStackTrace();
            userCredentialIsSaved = false;
        }
        return userCredentialIsSaved;
    }
}
