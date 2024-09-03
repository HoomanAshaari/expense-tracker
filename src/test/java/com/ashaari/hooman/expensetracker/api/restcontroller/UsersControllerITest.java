package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.common.dto.SignupRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static com.ashaari.hooman.expensetracker.api.restcontroller.util.ControllerTestUtils.EXPENSE_TRACKER_API_V_1;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
class UsersControllerITest {

    public static final String USERS_ENDPOINT = EXPENSE_TRACKER_API_V_1 + "/users";
    public static String SIGN_UP_ENDPOINT = USERS_ENDPOINT + "/signup";

    @Container
    private static final MySQLContainer<?> MYSQL_CONTAINER =
            new MySQLContainer<>(DockerImageName.parse("mysql:8.4.2"));

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MYSQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MYSQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MYSQL_CONTAINER::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
    }

    @Test
    void signUp_givenNewValidUser_signsUp() {
        SignupRequestDto signupRequestDto =
                new SignupRequestDto("Hooman", "12345678", "ashaari.hooman@gmail.com");

    }

    @Test
    void signUp_giveNewExistingUser_returnsUserNameAlreadyExistsError() {

    }

    @Test
    void login_givenValidUserCredential_returnsJwtToken() {

    }

    @Test
    void login_givenInvalidUserCredential_returns401Error() {

    }

}
