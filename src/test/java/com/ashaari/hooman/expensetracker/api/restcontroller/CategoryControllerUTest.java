package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.business.expense.service.CategoryService;
import com.ashaari.hooman.expensetracker.common.dto.AddCategoryRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddCategoryResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.CategoryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static com.ashaari.hooman.expensetracker.api.restcontroller.util.ControllerTestUtils.EXPENSE_TRACKER_API_V_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerUTest {

    public static String CATEGORY_ENDPOINT = EXPENSE_TRACKER_API_V_1 + "/categories";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryService categoryService;

    @Test
    @SneakyThrows
    void addCategory_givenNewCategory_savesAndReturnsTheCategory() {
        // Given
        AddCategoryRequestDto entertainment =
                new AddCategoryRequestDto("Entertainment", BigDecimal.valueOf(100));
        AddCategoryResponseDto expectedAddCategoryResponseDto = new AddCategoryResponseDto("1");
        given(categoryService.addCategory(entertainment)).willReturn(expectedAddCategoryResponseDto);
        // Act
        String requestBody = objectMapper.writeValueAsString(entertainment);
        MvcResult actualMvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(CATEGORY_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andReturn();
        // Assert
        AddCategoryResponseDto actualResponseDto = objectMapper.readValue(
                actualMvcResult.getResponse().getContentAsString(), AddCategoryResponseDto.class);
        assertEquals("1", actualResponseDto.id());
        verify(categoryService, times(1)).addCategory(entertainment);
    }

    @Test
    @SneakyThrows
    void getCategory_givenExistingCategoryId_returnsTheCategory() {
        // Given
        CategoryDto expectedCategory = new CategoryDto(
                "1", "Entertainment", BigDecimal.valueOf(100));
        given(categoryService.getCategory("1")).willReturn(expectedCategory);
        // Act
        MvcResult actualMvcResult = this.mockMvc
                .perform(MockMvcRequestBuilders.get(CATEGORY_ENDPOINT + "/{id}", "1")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        // Assert
        CategoryDto actualResponseDto = objectMapper.readValue(
                actualMvcResult.getResponse().getContentAsString(), CategoryDto.class);
        verify( categoryService, times(1)).getCategory("1");
        assertEquals("1", actualResponseDto.id());
    }

}
