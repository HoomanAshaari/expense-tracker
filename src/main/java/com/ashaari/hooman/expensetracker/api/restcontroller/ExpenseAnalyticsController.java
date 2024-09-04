package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.business.expense.service.AnalyticsService;
import com.ashaari.hooman.expensetracker.common.dto.CategoryExpenseStatsDto;
import com.ashaari.hooman.expensetracker.logger.Logged;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static com.ashaari.hooman.expensetracker.api.restcontroller.util.RestUtils.EXPENSE_ANALYTICS_API_V_1;

@RestController
@RequestMapping(EXPENSE_ANALYTICS_API_V_1)
@RequiredArgsConstructor
@Logged
@Tag(name = "Analytics")
@Validated
public class ExpenseAnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/category-expenses")
    @Operation(description =
            "Returns statistics about money spent in each category, in the given time range")
    public CategoryExpenseStatsDto getCategoryExpenseStatistics(
            @RequestParam("dateFrom")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDate dateFrom,
            @RequestParam("dateTo")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDate dateTo) {
        return null;
    }

}
