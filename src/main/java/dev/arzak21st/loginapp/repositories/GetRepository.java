package dev.arzak21st.loginapp.repositories;

import dev.arzak21st.loginapp.models.User;
import dev.arzak21st.loginapp.models.UserCredential;
import java.sql.ResultSet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class GetRepository {

    /* ========== VARIABLES ========== */
    User user;
    UserCredential userCredential;

    Integer lastSavedUserId;
    boolean userCredentialExists;

    String sqlQuery;

    Object[] sqlQueryParameters;

    JdbcTemplate jdbcTemplate;

    /* ========== CONSTRUCTORS ========== */
    public GetRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /* ========== METHODS ========== */
    public Integer getLastSavedUserId() {

        lastSavedUserId = null;

        try {

            sqlQuery
                    = "SELECT MAX(user_id) \n"
                    + "FROM user";

            lastSavedUserId = jdbcTemplate.queryForObject(sqlQuery, Integer.class);
        }
        catch(Exception exception) {
            // exception.printStackTrace();
            lastSavedUserId = null;
        }
        return lastSavedUserId;
    }

    public User getUserById(Integer userId) {

        user = null;

        try {

            sqlQuery
                    = "SELECT * \n"
                    + "FROM user \n"
                    + "WHERE user.user_id = ?";

            sqlQueryParameters = new Object[] {userId};

            user = jdbcTemplate.queryForObject(sqlQuery, sqlQueryParameters, userRowMapper);
        }
        catch(Exception exception) {
            // exception.printStackTrace();
            user = null;
        }
        return user;
    }

    public UserCredential getUserCredentialById(Integer userId) {

        userCredential = null;

        try {

            sqlQuery
                    = "SELECT * \n"
                    + "FROM user_credential \n"
                    + "WHERE user_credential.user_id = ?";

            sqlQueryParameters = new Object[] {userId};

            userCredential = jdbcTemplate.queryForObject(sqlQuery, sqlQueryParameters, userCredentialRowMapper);
        }
        catch(Exception exception) {
            // exception.printStackTrace();
            userCredential = null;
        }
        return userCredential;
    }

    public UserCredential getUserCredentialByEmail(String email) {

        userCredential = null;

        try {

            sqlQuery
                    = "SELECT * \n"
                    + "FROM user_credential \n"
                    + "WHERE user_credential.email = ?";

            sqlQueryParameters = new Object[] {email};

            userCredential = jdbcTemplate.queryForObject(sqlQuery, sqlQueryParameters, userCredentialRowMapper);
        }
        catch(Exception exception) {
            // exception.printStackTrace();
            userCredential = null;
        }
        return userCredential;
    }

    public UserCredential getUserCredentialByEmailAndPassword(String email, String password) {

        userCredential = null;

        try {

            sqlQuery
                    = "SELECT * \n"
                    + "FROM user_credential \n"
                    + "WHERE user_credential.email = ? \n"
                    + "AND user_credential.password = ?";

            sqlQueryParameters = new Object[] {email, password};

            userCredential = jdbcTemplate.queryForObject(sqlQuery, sqlQueryParameters, userCredentialRowMapper);
        }
        catch(Exception exception) {
            // exception.printStackTrace();
            userCredential = null;
        }
        return userCredential;
    }

    public UserCredential getUserCredentialByUsernameOrEmail(String username, String email) {

        userCredential = null;

        try {

            sqlQuery
                    = "SELECT * \n"
                    + "FROM user_credential \n"
                    + "WHERE user_credential.username = ? \n"
                    + "OR user_credential.email = ?";

            sqlQueryParameters = new Object[] {username, email};

            userCredential = jdbcTemplate.queryForObject(sqlQuery, sqlQueryParameters, userCredentialRowMapper);
        }
        catch(Exception exception) {
            // exception.printStackTrace();
            userCredential = null;
        }
        return userCredential;
    }

    public boolean getUserCredentialByIdAndPassword(Integer userId, String password) {

        userCredentialExists = false;

        try {

            sqlQuery
                    = "SELECT * \n"
                    + "FROM user_credential \n"
                    + "WHERE user_credential.user_id = ? \n"
                    + "AND user_credential.password = ?";

            sqlQueryParameters = new Object[] {userId, password};

            userCredential = jdbcTemplate.queryForObject(sqlQuery, sqlQueryParameters, userCredentialRowMapper);

            if(userCredential != null) {
                userCredentialExists = true;
            }
        }
        catch(Exception exception) {
            // exception.printStackTrace();
            userCredentialExists = false;
        }
        return userCredentialExists;
    }

    /* ========== OTHER METHODS ========== */
    private RowMapper<User> userRowMapper = (ResultSet userRS, int rowNum) -> {

        User user = new User(
                userRS.getInt(1),
                userRS.getString(2),
                userRS.getString(3),
                userRS.getDate(4),
                userRS.getString(5),
                userRS.getString(6)
        );
        return user;
    };

    private RowMapper<UserCredential> userCredentialRowMapper = (ResultSet userCredentialRS, int rowNum) -> {

        User user = getUserById(userCredentialRS.getInt(1));
        UserCredential userCredential = new UserCredential(
                userCredentialRS.getInt(1),
                userCredentialRS.getString(2),
                userCredentialRS.getString(3),
                userCredentialRS.getString(4),
                user
        );
        return userCredential;
    };
}
