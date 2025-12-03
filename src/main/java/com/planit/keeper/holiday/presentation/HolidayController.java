package com.planit.keeper.holiday.presentation;

import com.planit.keeper.holiday.application.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/holidays")
public class HolidayController {
    private final HolidayService holidayService;

    @GetMapping
    public void saveHolidaysLastFiveYears() {
        holidayService.saveHolidaysLastFiveYears();
    }
}
