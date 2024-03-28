package dev.arzak21st.loginapp.repositories;

import dev.arzak21st.loginapp.repositories.GetRepository;
import dev.arzak21st.loginapp.models.User;
import dev.arzak21st.loginapp.models.UserCredential;
import dev.arzak21st.loginapp.utilities.ApplicationContextUtility;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetRepositoryTest extends ApplicationContextUtility {

    GetRepository getRepository;

    @BeforeAll
    public void beforeAll() {
        getRepository = (GetRepository) getApplicationContext().getBean("getRepository");
    }

    @Disabled
    @Nested
    public class GetLastSavedUserIdTest {

        @Disabled
        @Test
        @DisplayName("Existing userId should be retreived")
        public void testGetLastSavedUserId_ExistingUserId() {

            // Make sure to have an existing user with the same userId
            Integer expectedResult = 24;
            Integer actualResult = getRepository.getLastSavedUserId();

            assertEquals(expectedResult, actualResult);
        }

        @Disabled
        @Test
        @DisplayName("Non existing userId should NOT be retreived")
        public void testGetLastSavedUserId_NonExistingUserId() {

            // Make sure to have no existing user
            Integer expectedResult = null;
            Integer actualResult = getRepository.getLastSavedUserId();

            assertEquals(expectedResult, actualResult);
        }
    }

    @Disabled
    @Nested
    public class GetUserByIdTest {

        @Disabled
        @Test
        @DisplayName("Existing user should be retreived by userId")
        public void testGetUserById_ExistingUser() {

            // Make sure to have an existing user with the same userId
            Integer userId = 24;
            String firstName = "Wassim";
            String lastName = "Arzak";
            Date dateOfBirth = Date.valueOf("2000-05-21");
            String gender = "Male";
            String country = "Morocco";

            User expectedResult = new User(userId, firstName, lastName, dateOfBirth, gender, country);
            User actualResult = getRepository.getUserById(userId);

            assertEquals(expectedResult, actualResult);
        }

        @Disabled
        @Test
        @DisplayName("Non existing user should NOT be retreived by userId")
        public void testGetUserById_NonExistingUser() {

            // Make sure to have no existing user with the same userId
            Integer userId = 0;

            User expectedResult = null;
            User actualResult = getRepository.getUserById(userId);

            assertEquals(expectedResult, actualResult);

            userId = null;

            expectedResult = null;
            actualResult = getRepository.getUserById(userId);

            assertEquals(expectedResult, actualResult);
        }
    }

    @Disabled
    @Nested
    public class GetUserCredentialByIdTest {

        @Disabled
        @Test
        @DisplayName("Existing userCredential should be retreived by userId")
        public void testGetUserCredentialById_ExistingUserCredential() {

            // Make sure to have an existing userCredential with the same userId
            Integer userId = 24;
            String username = "arzak";
            String email = "arzak.wassim@gmail.com";
            String password = "123";

            UserCredential expectedResult = new UserCredential(userId, username, email, password);
            UserCredential actualResult = getRepository.getUserCredentialById(userId);

            assertEquals(expectedResult, actualResult);
        }

        @Disabled
        @Test
        @DisplayName("Non existing userCredential should NOT be retreived by userId")
        public void testGetUserCredentialById_NonExistingUserCredential() {

            // Make sure to have no existing userCredential with the same userId
            Integer userId = 0;

            UserCredential expectedResult = null;
            UserCredential actualResult = getRepository.getUserCredentialById(userId);

            assertEquals(expectedResult, actualResult);

            userId = null;

            expectedResult = null;
            actualResult = getRepository.getUserCredentialById(userId);

            assertEquals(expectedResult, actualResult);
        }
    }

    @Disabled
    @Nested
    public class GetUserCredentialByEmailTest {

        @Disabled
        @Test
        @DisplayName("Existing userCredential should be retreived by email")
        public void testGetUserCredentialByEmail_ExistingUserCredential() {

            // Make sure to have an existing userCredential with the same email
            Integer userId = 24;
            String username = "arzak";
            String email = "arzak.wassim@gmail.com";
            String password = "123";

            UserCredential expectedResult = new UserCredential(userId, username, email, password);
            UserCredential actualResult = getRepository.getUserCredentialByEmail(email);

            assertEquals(expectedResult, actualResult);
        }

        @Disabled
        @Test
        @DisplayName("Non existing userCredential should NOT be retreived by email")
        public void testGetUserCredentialByEmail_NonExistingUserCredential() {

            // Make sure to have no existing userCredential with the same email
            String email = "";

            UserCredential expectedResult = null;
            UserCredential actualResult = getRepository.getUserCredentialByEmail(email);

            assertEquals(expectedResult, actualResult);

            email = null;

            expectedResult = null;
            actualResult = getRepository.getUserCredentialByEmail(email);

            assertEquals(expectedResult, actualResult);
        }
    }

    @Disabled
    @Nested
    public class GetUserCredentialByEmailAndPasswordTest {

        @Disabled
        @Test
        @DisplayName("Existing userCredential should be retreived by email and password")
        public void testGetUserCredentialByEmailAndPassword_ExistingUserCredential() {

            // Make sure to have an existing userCredential with the same email and password
            Integer userId = 24;
            String username = "arzak";
            String email = "arzak.wassim@gmail.com";
            String password = "123";

            UserCredential expectedResult = new UserCredential(userId, username, email, password);
            UserCredential actualResult = getRepository.getUserCredentialByEmailAndPassword(email, password);

            assertEquals(expectedResult, actualResult);
        }

        @Disabled
        @Test
        @DisplayName("Non existing userCredential should NOT be retreived by email and password")
        public void testGetUserCredentialByEmailAndPassword_NonExistingUserCredential() {

            // Make sure to have no existing userCredential with the same email and password
            String email = "";
            String password = "";

            UserCredential expectedResult = null;
            UserCredential actualResult = getRepository.getUserCredentialByEmailAndPassword(email, password);

            assertEquals(expectedResult, actualResult);

            email = null;

            expectedResult = null;
            actualResult = getRepository.getUserCredentialByEmailAndPassword(email, password);

            assertEquals(expectedResult, actualResult);
        }
    }

    @Disabled
    @Nested
    public class GetUserCredentialByUsernameOrEmailTest {

        @Disabled
        @Test
        @DisplayName("Existing userCredential should be retreived by username or email")
        public void testGetUserCredentialByUsernameOrEmail_ExistingUserCredential() {

            // Make sure to have an existing userCredential with the same username or email
            Integer userId = 24;
            String username = "arzak";
            String email = "arzak.wassim@gmail.com";
            String password = "123";

            UserCredential expectedResult = new UserCredential(userId, username, email, password);
            UserCredential actualResult = getRepository.getUserCredentialByUsernameOrEmail(username, email);

            assertEquals(expectedResult, actualResult);
        }

        @Disabled
        @Test
        @DisplayName("Non existing userCredential should NOT be retreived by username or email")
        public void testGetUserCredentialByUsernameOrEmail_NonExistingUserCredential() {

            // Make sure to have no existing userCredential with the same username or email
            String username = "";
            String email = "";

            UserCredential expectedResult = null;
            UserCredential actualResult = getRepository.getUserCredentialByUsernameOrEmail(username, email);

            assertEquals(expectedResult, actualResult);

            email = null;

            expectedResult = null;
            actualResult = getRepository.getUserCredentialByUsernameOrEmail(username, email);

            assertEquals(expectedResult, actualResult);
        }
    }

    @Disabled
    @Nested
    public class GetUserCredentialByIdAndPasswordTest {

        @Disabled
        @Test
        @DisplayName("Existing userCredential should be retreived by userId and password")
        public void testGetUserCredentialByIdAndPassword_ExistingUserCredential() {

            // Make sure to have an existing userCredential with the same userId and password
            Integer userId = 24;
            String password = "123";

            boolean expectedResult = true;
            boolean actualResult = getRepository.getUserCredentialByIdAndPassword(userId, password);

            assertEquals(expectedResult, actualResult);
        }

        @Disabled
        @Test
        @DisplayName("Non existing userCredential should NOT be retreived by userId and password")
        public void testGetUserCredentialByIdAndPassword_NonExistingUserCredential() {

            // Make sure to have no existing userCredential with the same userId and password
            Integer userId = 0;
            String password = "";

            boolean expectedResult = false;
            boolean actualResult = getRepository.getUserCredentialByIdAndPassword(userId, password);

            assertEquals(expectedResult, actualResult);

            userId = null;
            password = null;

            expectedResult = false;
            actualResult = getRepository.getUserCredentialByIdAndPassword(userId, password);

            assertEquals(expectedResult, actualResult);
        }
    }

}
/*
How to test a method
====================
1. Create an instance of the class under test
2. Set up inputs to be passed to the method
3. Execute the code that we want to test
4. Verify the result (expected result VS actual result) using assertion methods
 */
