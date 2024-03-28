package dev.arzak21st.loginapp.repositories;

import dev.arzak21st.loginapp.models.User;
import org.springframework.jdbc.core.JdbcTemplate;

public class UpdateRepository {

    /* ========== VARIABLES ========== */
    boolean userIsUpdated;
    boolean passwordIsUpdated;

    Integer updating;

    String sqlQuery;

    Object[] sqlQueryParameters;

    JdbcTemplate jdbcTemplate;

    /* ========== CONSTRUCTORS ========== */
    public UpdateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /* ========== METHODS ========== */
    public boolean updateUser(User user) {

        userIsUpdated = false;

        try {

            sqlQuery
                    = "UPDATE user \n"
                    + "SET user.first_name = ?, \n"
                    + "user.last_name = ?, \n"
                    + "user.date_of_birth = ?, \n"
                    + "user.gender = ?, \n"
                    + "user.country = ? \n"
                    + "WHERE user.user_id = ? ";

            sqlQueryParameters = new Object[] {
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getGender(),
                user.getCountry(),
                user.getUserId()
            };

            updating = jdbcTemplate.update(sqlQuery, sqlQueryParameters);

            if(updating == 1) {
                userIsUpdated = true;
            }
        }
        catch(Exception exception) {
            // exception.printStackTrace();
            userIsUpdated = false;
        }
        return userIsUpdated;
    }

    public boolean updatePassword(String newPassword, Integer userId) {

        passwordIsUpdated = false;

        try {

            sqlQuery
                    = "UPDATE user_credential \n"
                    + "SET user_credential.password = ? \n"
                    + "WHERE user_credential.user_id = ? ";

            sqlQueryParameters = new Object[] {newPassword, userId};

            updating = jdbcTemplate.update(sqlQuery, sqlQueryParameters);

            if(updating == 1) {
                passwordIsUpdated = true;
            }
        }
        catch(Exception exception) {
            // exception.printStackTrace();
            passwordIsUpdated = false;
        }
        return passwordIsUpdated;
    }
}
