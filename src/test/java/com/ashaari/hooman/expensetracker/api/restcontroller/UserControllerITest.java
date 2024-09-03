package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.common.dto.ExceptionDto;
import com.ashaari.hooman.expensetracker.common.dto.SignUpRequestDto;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
class UserControllerITest {

    public static final String USERS_ENDPOINT = EXPENSE_TRACKER_API_V_1 + "/users";
    public static String SIGN_UP_ENDPOINT = USERS_ENDPOINT + "/sign-up";
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
        SignUpRequestDto john =
                new SignUpRequestDto("John", "12345678", "John@gmail.com");
        this.mockMvc.perform(MockMvcRequestBuilders.post(SIGN_UP_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(john)))
                .andExpect(status().isCreated());

        // Act, Assert
        MvcResult actualMvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(SIGN_UP_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(john)))
                .andExpect(status().is4xxClientError())
                .andReturn();
        ExceptionDto actualExceptionDto = objectMapper.readValue(
                actualMvcResult.getResponse().getContentAsString(), ExceptionDto.class);
        assertEquals(UsernameAlreadyExistsException.class.getSimpleName(), actualExceptionDto.errorCode());
    }

    @Test
    void login_givenValidUserCredential_returnsJwtToken() {

    }

    @Test
    void login_givenInvalidUserCredential_returns401Error() {

    }

}
