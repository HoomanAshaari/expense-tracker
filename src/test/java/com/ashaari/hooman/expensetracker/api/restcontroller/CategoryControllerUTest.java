package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.ashaari.hooman.expensetracker.api.restcontroller.util.ControllerTestUtils.EXPENSE_TRACKER_API_V_1;

@WebMvcTest(CategoryController.class)
class CategoryControllerUTest {

    public static String CATEGORY_ENDPOINT = EXPENSE_TRACKER_API_V_1 + "/categories";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void addCategory_givenNewCategory_savesAndReturnsTheCategory() {

    }

}
