package com.planit.keeper.holiday.application;

import com.planit.keeper.country.application.CountryService;
import com.planit.keeper.country.domain.Country;
import com.planit.keeper.holiday.domain.Holiday;
import com.planit.keeper.holiday.domain.HolidayClient;
import com.planit.keeper.holiday.infra.HolidayRepository;
import com.planit.keeper.holiday.presentation.dto.HolidayResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HolidayService {
    private static final Integer YEAR_RANGE = 5;
    private static final Integer SCHEDULER_YEAR_RANGE = 2;

    private final CountryService countryService;
    private final HolidayRepository holidayRepository;
    private final HolidayClient holidayClient;

    @Transactional
    public void saveHolidaysLastFiveYears() {
        checkHolidayIsEmpty();

        List<Integer> recentYears = getYearsFromNow(YEAR_RANGE);
        List<Country> savedCountry = countryService.saveCountryCodeAndLoad();

        List<Holiday> target = fetchHolidaysByYearsAndCountries(recentYears, savedCountry);
        holidayRepository.saveAll(target);
    }

    @Transactional
    public void delete(Integer year, String countryCode) {
        countryService.findCountryOrThrow(countryCode);
        holidayRepository.deleteAllByHolidayYearAndCountryCode(year, countryCode);
    }

    @Transactional(readOnly = true)
    public Page<Holiday> findHolidays(Integer year, String countryCode, Pageable pageable) {
        if (year == null && countryCode == null) {
            throw new RuntimeException("공휴일 데이터 검색시에 year 과 countryCode 는 반드시 입력하셔야 합니다.");
        }

        if (countryCode != null) {
            countryService.findCountryOrThrow(countryCode);
        }

        if (year == null) {
            return holidayRepository.findAllByCountryCode(countryCode, pageable);
        }

        if (countryCode == null) {
            return holidayRepository.findAllByHolidayYear(year, pageable);
        }

        return holidayRepository.findAllByHolidayYearAndCountryCode(year, countryCode, pageable);
    }

    @Transactional
    public void refresh(Integer year, String countryCode) {
        countryService.findCountryOrThrow(countryCode);

        holidayRepository.deleteAllByHolidayYearAndCountryCode(year, countryCode);

        List<HolidayResponse> responses = holidayClient.getPublicHolidays(countryCode, year);
        List<Holiday> holidays = convertToHolidays(responses);

        holidayRepository.saveAll(holidays);
    }

    @Transactional
    public void refreshPreviousAndCurrentYear() {
        List<Integer> yearsToRefresh = getYearsFromNow(SCHEDULER_YEAR_RANGE);
        List<Country> countries = countryService.findAllCountry();

        countries.forEach(country -> yearsToRefresh.forEach(year -> refresh(year, country.getCountryCode())));
    }

    private List<Integer> getYearsFromNow(int range) {
        int now = LocalDate.now().getYear();
        return IntStream.rangeClosed(now - (range - 1), now).boxed().toList();
    }

    private void checkHolidayIsEmpty() {
        if (holidayRepository.count() > 0) {
            throw new RuntimeException("이미 공휴일 데이터가 저장되어 있습니다.");
        }
    }

    private List<Holiday> fetchHolidaysByYearsAndCountries(List<Integer> recentYears, List<Country> countries) {
        return recentYears.parallelStream()
                .flatMap(year -> countries.stream()
                        .flatMap(country -> fetchHolidaysByCountryAndYear(country.getCountryCode(), year)))
                .toList();
    }

    private Stream<Holiday> fetchHolidaysByCountryAndYear(String countryCode, int year) {
        List<HolidayResponse> responses = holidayClient.getPublicHolidays(countryCode, year);
        return convertToHolidays(responses).stream();
    }

    private List<Holiday> convertToHolidays(List<HolidayResponse> responses) {
        return responses.stream()
                .map(hd -> new Holiday(hd.getDate(), hd.getLocalName(), hd.getName(), hd.getCountryCode(), hd.isFixed(),
                        hd.isGlobal(), hd.getCounties(), hd.getLaunchYear(), hd.getTypes())).toList();
    }

}
