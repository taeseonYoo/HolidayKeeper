package com.planit.keeper.holiday.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.planit.keeper.country.application.CountryService;
import com.planit.keeper.country.domain.Country;
import com.planit.keeper.holiday.domain.Holiday;
import com.planit.keeper.holiday.domain.HolidayClient;
import com.planit.keeper.holiday.infra.HolidayRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HolidayService {
    private final CountryService countryService;
    private final HolidayRepository holidayRepository;
    private final HolidayClient holidayClient;
    private final HolidayMapper holidayMapper;
    @Transactional
    public void saveHolidaysLastFiveYears() {
        //최초 실행인지 확인?
        int nowYear = LocalDate.now().getYear();
        List<Country> savedCountry = countryService.save();

        for (int year = nowYear-4; year <= nowYear; year++) {
            for (Country country : savedCountry) {
                String json = holidayClient.getPublicHolidays(country.getCountryCode(), year);

                List<HolidayResponse> holidays;
                try {
                    holidays = holidayMapper.toHolidayResponses(json);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("");
                }
                List<Holiday> holidayEntities = holidays.stream()
                        .map(hd -> new Holiday(hd.getDate(), hd.getLocalName(), hd.getName(), hd.getCountryCode(),
                                hd.isFixed(), hd.isGlobal(), hd.getCounties(), hd.getLaunchYear(), hd.getTypes()))
                        .toList();
                holidayRepository.saveAll(holidayEntities);
            }
        }
    }

    @Transactional
    public void delete(Integer year,String countryCode) {
        if (year == null && countryCode == null) {
            throw new RuntimeException("");
        } else if (year == null && countryCode != null) {
            countryService.findCountryOrThrow(countryCode);
            holidayRepository.deleteAllByCountryCode(countryCode);
        } else if (year != null && countryCode == null) {
            holidayRepository.deleteAllByYear(year);
        } else if (year != null && countryCode != null) {
            countryService.findCountryOrThrow(countryCode);
            holidayRepository.deleteAllByYearAndCountryCode(year, countryCode);
        }
    }
}
