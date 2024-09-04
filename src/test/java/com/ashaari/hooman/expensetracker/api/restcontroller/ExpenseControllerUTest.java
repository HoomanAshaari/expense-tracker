package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.api.restcontroller.util.ControllerTestUtils;
import com.ashaari.hooman.expensetracker.business.expense.service.ExpenseService;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseUpdateDto;
import com.ashaari.hooman.expensetracker.common.exception.client.CategoryNotFoundException;
import com.ashaari.hooman.expensetracker.common.exception.client.ExpenseNotFoundException;
import com.ashaari.hooman.expensetracker.filter.JwtAuthFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        value = ExpenseController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = JwtAuthFilter.class))
class ExpenseControllerUTest {

    public static final String EXPENSES_ENDPOINT = ControllerTestUtils.EXPENSE_TRACKER_API_V_1 + "/expenses";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ExpenseService expenseService;

    @Test
    @SneakyThrows
    void addExpense_givenNewExpense_savesAndReturnsTheExpense() {
        // Given
        AddExpenseRequestDto justHadACoffee = new AddExpenseRequestDto(
                BigDecimal.TEN, "Just had a coffee", "1", LocalDateTime.now());
        String requestBody = objectMapper.writeValueAsString(
                justHadACoffee);
        // Act
        given(expenseService.addExpense(justHadACoffee)).willReturn(new AddExpenseResponseDto("10"));
        MvcResult actualMvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(EXPENSES_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andReturn();
        // Assert
        AddExpenseResponseDto addExpenseResponseDto = objectMapper.readValue(
                actualMvcResult.getResponse().getContentAsString(), AddExpenseResponseDto.class);
        assertEquals("10", addExpenseResponseDto.id());
        verify(expenseService, times(1)).addExpense(justHadACoffee);
    }

    @Test
    @SneakyThrows
    void addExpense_givenNewExpenseWithNotExistingCategory_returnsBadRequest() {
        // Given
        AddExpenseRequestDto homeToWorkCab = new AddExpenseRequestDto(
                BigDecimal.TEN, "Home to work cab", "2", LocalDateTime.now());
        String requestBody = objectMapper.writeValueAsString(
                homeToWorkCab);
        given(expenseService.addExpense(homeToWorkCab)).willThrow(new CategoryNotFoundException());
        // Act
        this.mockMvc.perform(MockMvcRequestBuilders.post(EXPENSES_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().is4xxClientError());
        //Assert
        verify(expenseService, times(1)).addExpense(homeToWorkCab);
    }

    @Test
    @SneakyThrows
    void getExpense_givenExistingExpenseId_returnsExpense() {
        // Given
        ExpenseDto expectedExpense = new ExpenseDto(
                "1", BigDecimal.ONE, "Water", "10", LocalDateTime.now());
        given(expenseService.getExpense("1")).willReturn(expectedExpense);
        // Act
        MvcResult actualMvcResult = this.mockMvc
                .perform(MockMvcRequestBuilders.get(EXPENSES_ENDPOINT + "/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        // Assert
        ExpenseDto actualExpense = objectMapper.readValue(
                actualMvcResult.getResponse().getContentAsString(), ExpenseDto.class
        );
        verify(expenseService, times(1)).getExpense("1");
        assertEquals(expectedExpense, actualExpense);
    }

    @Test
    @SneakyThrows
    void getExpense_givenNotExistingExpenseId_returnsNotFound() {
        // Given
        given(expenseService.getExpense("2")).willThrow(new ExpenseNotFoundException());
        // Act
        this.mockMvc
                .perform(MockMvcRequestBuilders.get(EXPENSES_ENDPOINT + "/{id}", "2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

        // Assert
        verify(expenseService, times(1)).getExpense("2");
    }

    @Test
    @SneakyThrows
    void deleteExpense_givenExpenseId_performsDelete() {
        // Given, Act
        this.mockMvc
                .perform(MockMvcRequestBuilders.delete(EXPENSES_ENDPOINT + "/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());
        // Assert
        verify(expenseService, times(1)).removeExpense("1");
    }


    @Test
    @SneakyThrows
    void updateExpense_givenUpdatedExpenseContent_savesAndReturnsTheUpdatedExpense() {
        // Given
        ExpenseUpdateDto paidTheRent =
                new ExpenseUpdateDto("1", null, "Paid the rent", null);
        String requestBody = objectMapper.writeValueAsString(paidTheRent);
        ExpenseDto expectedExpenseDto = new ExpenseDto(
                paidTheRent.id(), BigDecimal.valueOf(2000), paidTheRent.description(),
                "5", LocalDateTime.of(2024, 9, 2, 1, 0));
        given(expenseService.partialUpdate(paidTheRent)).willReturn(expectedExpenseDto);
        // Act
        MvcResult actualMvcResult = this.mockMvc.perform(MockMvcRequestBuilders.patch(EXPENSES_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
        // Assert
        ExpenseDto actualResult = objectMapper.readValue(
                actualMvcResult.getResponse().getContentAsString(), ExpenseDto.class);
        assertEquals(expectedExpenseDto.categoryId(), actualResult.categoryId());
        assertEquals(expectedExpenseDto.amount(), actualResult.amount());
        assertEquals(paidTheRent.description(), actualResult.description());
        assertEquals(expectedExpenseDto.spentOn(), actualResult.spentOn());
    }

}
