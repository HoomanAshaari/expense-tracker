package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.common.dto.ExceptionDto;
import com.ashaari.hooman.expensetracker.common.dto.LoginRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.LoginResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.SignUpRequestDto;
import com.ashaari.hooman.expensetracker.common.exception.client.InvalidCredentialsException;
import com.ashaari.hooman.expensetracker.common.exception.client.UsernameAlreadyExistsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static com.ashaari.hooman.expensetracker.api.restcontroller.util.ControllerTestUtils.EXPENSE_TRACKER_API_V_1;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
class UserControllerITest {

    public static final String USERS_ENDPOINT = EXPENSE_TRACKER_API_V_1 + "/users";
    public static String SIGN_UP_ENDPOINT = USERS_ENDPOINT + "/sign-up";
    public static String LOGIN_ENDPOINT = USERS_ENDPOINT + "/login";
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

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
    @SneakyThrows
    void signUp_givenNewValidUser_signUpsTheUser() {
        // Given
        SignUpRequestDto signUpRequestDto =
                new SignUpRequestDto("Hooman", "12345678", "ashaari.hooman@gmail.com");
        // Act, Assert
        this.mockMvc.perform(MockMvcRequestBuilders.post(SIGN_UP_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(signUpRequestDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @SneakyThrows
    void signUp_giveNewExistingUser_returnsUserNameAlreadyExistsError() {
        // Given
        SignUpRequestDto bob =
                new SignUpRequestDto("bob", "12345678", "bob@gmail.com");
        this.mockMvc.perform(MockMvcRequestBuilders.post(SIGN_UP_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(bob)))
                .andExpect(status().isCreated());

        // Act, Assert
        MvcResult actualMvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(SIGN_UP_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(bob)))
                .andExpect(status().is4xxClientError())
                .andReturn();
        ExceptionDto actualExceptionDto = objectMapper.readValue(
                actualMvcResult.getResponse().getContentAsString(), ExceptionDto.class);
        assertEquals(UsernameAlreadyExistsException.class.getSimpleName(), actualExceptionDto.errorCode());
    }

    @Test
    @SneakyThrows
    void login_givenValidUserCredential_returnsJwtToken() {
        // Given
        SignUpRequestDto johnSignUpRequestDto =
                new SignUpRequestDto("John", "12345678", "John@gmail.com");
        LoginRequestDto johnLoginRequestDto = new LoginRequestDto("John", "12345678");
        this.mockMvc.perform(MockMvcRequestBuilders.post(SIGN_UP_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(johnSignUpRequestDto)));
        // Act, Assert
        MvcResult actualMvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(LOGIN_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(johnLoginRequestDto)))
                .andExpect(status().isOk())
                .andReturn();
        LoginResponseDto actualLoginResponseDto = objectMapper.readValue(
                actualMvcResult.getResponse().getContentAsString(), LoginResponseDto.class);
        assertThat(actualLoginResponseDto.token()).isNotBlank();
    }

    @Test
    @SneakyThrows
    void login_givenInvalidUserCredentials_returns401Error() {
        // Given
        LoginRequestDto aliceLoginRequestDto = new LoginRequestDto("Alice", "12345678");
        // Act, Assert
        MvcResult actualMvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(LOGIN_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(aliceLoginRequestDto)))
                .andExpect(status().isUnauthorized())
                .andReturn();
        ExceptionDto actualExceptionDto = objectMapper.readValue(
                actualMvcResult.getResponse().getContentAsString(), ExceptionDto.class);
        assertEquals(InvalidCredentialsException.class.getSimpleName(), actualExceptionDto.errorCode());
    }

}
