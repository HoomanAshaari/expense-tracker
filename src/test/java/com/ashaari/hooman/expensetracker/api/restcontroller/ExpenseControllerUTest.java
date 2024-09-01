package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.business.expense.service.ExpenseService;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseResponseDto;
import com.ashaari.hooman.expensetracker.common.exception.client.CategoryNotFoundException;
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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpenseController.class)
class ExpenseControllerUTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ExpenseService expenseService;

    @Test
    @SneakyThrows
    void addExpense_givenNewExpense_savesAndReturnsTheExpense() {
        ExpenseRequestDto justHadACoffee =
                new ExpenseRequestDto(BigDecimal.TEN, "Just had a coffee", "1", LocalDateTime.now());
        String requestBody = objectMapper.writeValueAsString(
                justHadACoffee);

        given(expenseService.addExpense(justHadACoffee)).willReturn(new ExpenseResponseDto("10"));
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/expense-tracker/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andReturn();

        ExpenseResponseDto expenseResponseDto = objectMapper.readValue(
                result.getResponse().getContentAsString(), ExpenseResponseDto.class);
        assertEquals("10", expenseResponseDto.id());
        verify(expenseService, times(1)).addExpense(justHadACoffee);
    }

    @Test
    @SneakyThrows
    void addExpense_givenNewExpenseWithNotExistingCategory_returnsBadRequest() {
        ExpenseRequestDto homeToWorkCab =
                new ExpenseRequestDto(BigDecimal.TEN, "Home to work cab", "2", LocalDateTime.now());
        String requestBody = objectMapper.writeValueAsString(
                homeToWorkCab);
        given(expenseService.addExpense(homeToWorkCab)).willThrow(new CategoryNotFoundException());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/expense-tracker/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().is4xxClientError());

        verify(expenseService, times(1)).addExpense(homeToWorkCab);
    }

    @Test
    void getExpense_givenExistingExpenseId_returnsExpense() {

    }

}
