package com.planit.keeper.holiday.presentation;

import com.planit.keeper.holiday.application.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/holidays")
public class HolidayController {
    private final HolidayService holidayService;

    @PostMapping
    public void saveHolidaysLastFiveYears() {
        holidayService.saveHolidaysLastFiveYears();
    }

    @DeleteMapping
    public void deleteHolidays(@RequestParam(required = false) Integer year,
                               @RequestParam(required = false) String countryCode) {
        holidayService.delete(year, countryCode);
    }

    @GetMapping
    public void getHolidays(@RequestParam(required = false) Integer year,
                            @RequestParam(required = false) String countryCode,
                            @PageableDefault Pageable pageable) {
        holidayService.findHolidays(year,countryCode,pageable);
    }

    @PutMapping
    public void refreshHolidays(@RequestParam(required = false) Integer year,
                                @RequestParam(required = false) String countryCode) {
        holidayService.refresh(year, countryCode);
    }
}
