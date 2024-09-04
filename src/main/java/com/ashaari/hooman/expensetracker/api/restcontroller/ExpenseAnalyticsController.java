package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.business.expense.service.AnalyticsService;
import com.ashaari.hooman.expensetracker.common.dto.CategoryExpenseStatsDto;
import com.ashaari.hooman.expensetracker.logger.Logged;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static com.ashaari.hooman.expensetracker.api.restcontroller.util.RestUtils.EXPENSE_ANALYTICS_API_V_1;

@RestController
@RequestMapping(EXPENSE_ANALYTICS_API_V_1)
@RequiredArgsConstructor
@Logged
@Tag(name = "Expense Analytics")
@Validated
public class ExpenseAnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/category-expenses")
    @Operation(description =
            "Returns statistics about money spent in each category, within the given date range")
    public List<CategoryExpenseStatsDto> getCategoryExpenseStatistics(
            @RequestParam("dateTimeFrom")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @Parameter(example = "2024-09-01T19:52:45.150Z")
            LocalDateTime dateTimeFrom,
            @RequestParam("dateTimeTo")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @Parameter(example = "2024-09-04T19:52:45.150Z")
            LocalDateTime dateTimeTo) {
        return analyticsService.getCategoryExpenseStatistics(dateTimeFrom, dateTimeTo);
    }

}
