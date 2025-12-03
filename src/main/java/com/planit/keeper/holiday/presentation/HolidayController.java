package com.planit.keeper.holiday.presentation;

import com.planit.keeper.global.ApiResponse;
import com.planit.keeper.holiday.presentation.dto.HolidayResponse;
import com.planit.keeper.holiday.application.HolidayService;
import com.planit.keeper.holiday.domain.Holiday;
import com.planit.keeper.holiday.presentation.dto.DeleteHolidayRequest;
import com.planit.keeper.holiday.presentation.dto.PagedHolidayResponse;
import com.planit.keeper.holiday.presentation.dto.RefreshHolidayRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<Void>> saveHolidaysLastFiveYears() {
        holidayService.saveHolidaysLastFiveYears();
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(null));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteHolidays(@Valid @RequestBody DeleteHolidayRequest request) {
        holidayService.delete(request.getYear(), request.getCountryCode());
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PagedHolidayResponse>> getHolidays(@RequestParam(required = false) Integer year,
                                                                         @RequestParam(required = false) String countryCode,
                                                                         @PageableDefault Pageable pageable) {
        Page<Holiday> holidays = holidayService.findHolidays(year, countryCode, pageable);
        List<HolidayResponse> content = holidays.stream()
                .map(h -> new HolidayResponse(
                        h.getDate(),
                        h.getLocalName(),
                        h.getName(),
                        h.getCountryCode(),
                        h.isFixed(),
                        h.isGlobal(),
                        h.getCounties(),
                        h.getLaunchYear(),
                        h.getTypes()
                ))
                .toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(new PagedHolidayResponse(content, holidays.getNumber(), holidays.getSize(),
                        holidays.getTotalElements(), holidays.getTotalPages())));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Void>> refreshHolidays(@Valid @RequestBody RefreshHolidayRequest request) {
        holidayService.refresh(request.getYear(), request.getCountryCode());
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(null));
    }
}
