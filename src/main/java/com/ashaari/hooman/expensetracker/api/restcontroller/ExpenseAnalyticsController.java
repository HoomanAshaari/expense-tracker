package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.business.expense.service.AnalyticsService;
import com.ashaari.hooman.expensetracker.logger.Logged;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ashaari.hooman.expensetracker.api.restcontroller.util.RestUtils.EXPENSE_ANALYTICS_API_V_1;

@RestController
@RequestMapping(EXPENSE_ANALYTICS_API_V_1)
@RequiredArgsConstructor
@Logged
@Tag(name = "Analytics")
@Validated
public class ExpenseAnalyticsController {

    private final AnalyticsService analyticsService;

}
