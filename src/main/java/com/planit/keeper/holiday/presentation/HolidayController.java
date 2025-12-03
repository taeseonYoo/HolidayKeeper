package com.planit.keeper.holiday.presentation;

import com.planit.keeper.holiday.application.HolidayService;
import com.planit.keeper.holiday.presentation.dto.DeleteHolidayRequest;
import com.planit.keeper.holiday.presentation.dto.RefreshHolidayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public void deleteHolidays(@RequestBody DeleteHolidayRequest request) {
        holidayService.delete(request.getYear(), request.getCountryCode());
    }

    @GetMapping
    public void getHolidays(@RequestParam(required = false) Integer year,
                            @RequestParam(required = false) String countryCode,
                            @PageableDefault Pageable pageable) {
        holidayService.findHolidays(year,countryCode,pageable);
    }

    @PutMapping
    public void refreshHolidays(@RequestBody RefreshHolidayRequest request) {
        holidayService.refresh(request.getYear(), request.getCountryCode());
    }
}
