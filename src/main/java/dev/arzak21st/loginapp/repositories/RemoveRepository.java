package dev.arzak21st.loginapp.repositories;

import org.springframework.jdbc.core.JdbcTemplate;

public class RemoveRepository {

    /* ========== VARIABLES ========== */
    boolean userIsRemoved;

    Integer removing;

    String sqlQuery;

    Object[] sqlQueryParameters;

    JdbcTemplate jdbcTemplate;

    /* ========== CONSTRUCTORS ========== */
    public RemoveRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /* ========== METHODS ========== */
    public boolean removeUserById(Integer userId) {

        userIsRemoved = false;

        try {

            sqlQuery
                    = "DELETE FROM user \n"
                    + "WHERE user.user_id = ? ";

            sqlQueryParameters = new Object[] {userId};

            removing = jdbcTemplate.update(sqlQuery, sqlQueryParameters);

            if(removing == 1) {
                userIsRemoved = true;
            }
        }
        catch(Exception exception) {
            // exception.printStackTrace();
            userIsRemoved = false;
        }
        return userIsRemoved;
    }
}
