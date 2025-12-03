package com.planit.keeper.holiday.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.planit.keeper.country.application.CountryService;
import com.planit.keeper.country.domain.Country;
import com.planit.keeper.holiday.domain.Holiday;
import com.planit.keeper.holiday.domain.HolidayClient;
import com.planit.keeper.holiday.infra.HolidayRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HolidayService {
    private static final Integer YEAR_RANGE = 5;

    private final CountryService countryService;
    private final HolidayRepository holidayRepository;
    private final HolidayClient holidayClient;
    private final HolidayMapper holidayMapper;
    @Transactional
    public void saveHolidaysLastFiveYears() {
        //최초 실행인지 확인?
        List<Integer> recentYears = getRecentYears(YEAR_RANGE);
        List<Country> savedCountry = countryService.save();

        List<Holiday> target = recentYears.stream()
                .flatMap(year -> savedCountry.stream()
                        .flatMap(country -> {
                            String json = holidayClient.getPublicHolidays(country.getCountryCode(), year);
                            List<Holiday> holidays = convertJsonToHolidays(json);
                            return holidays.stream();
                        })
                )
                .toList();
        holidayRepository.saveAll(target);
    }

    @Transactional
    public void delete(Integer year,String countryCode) {
        if (year == null && countryCode == null) {
            throw new RuntimeException("");
        } else if (year == null && countryCode != null) {
            countryService.findCountryOrThrow(countryCode);
            holidayRepository.deleteAllByCountryCode(countryCode);
        } else if (year != null && countryCode == null) {
            holidayRepository.deleteAllByHolidayYear(year);
        } else if (year != null && countryCode != null) {
            countryService.findCountryOrThrow(countryCode);
            holidayRepository.deleteAllByHolidayYearAndCountryCode(year, countryCode);
        }
    }

    @Transactional(readOnly = true)
    public Page<Holiday> findHolidays(Integer year, String countryCode, Pageable pageable) {
        Page<Holiday> holidays = null;
        if (year == null && countryCode == null) {
            throw new RuntimeException("");
        } else if (year == null && countryCode != null) {
            countryService.findCountryOrThrow(countryCode);
            holidays = holidayRepository.findAllByCountryCode(countryCode,pageable);
        } else if (year != null && countryCode == null) {
            holidays = holidayRepository.findAllByHolidayYear(year,pageable);
        } else if (year != null && countryCode != null) {
            countryService.findCountryOrThrow(countryCode);
            holidays = holidayRepository.findAllByHolidayYearAndCountryCode(year, countryCode,pageable);
        }
        return holidays;
    }

    @Transactional
    public void refresh(Integer year, String countryCode) {
        countryService.findCountryOrThrow(countryCode);

        holidayRepository.deleteAllByHolidayYearAndCountryCode(year, countryCode);

        String json = holidayClient.getPublicHolidays(countryCode, year);

        List<Holiday> holidays = convertJsonToHolidays(json);
        holidayRepository.saveAll(holidays);
    }

    private List<Holiday> convertJsonToHolidays(String json) {
        List<HolidayResponse> holidays;
        try {
            holidays = holidayMapper.toHolidayResponses(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("");
        }
        return  holidays.stream()
                .map(hd -> new Holiday(hd.getDate(), hd.getLocalName(), hd.getName(), hd.getCountryCode(),
                        hd.isFixed(), hd.isGlobal(), hd.getCounties(), hd.getLaunchYear(), hd.getTypes()))
                .toList();
    }

    private List<Integer> getRecentYears(int range) {
        int now = LocalDate.now().getYear();
        return IntStream.rangeClosed(now-(range-1),now).boxed().toList();
    }
}
