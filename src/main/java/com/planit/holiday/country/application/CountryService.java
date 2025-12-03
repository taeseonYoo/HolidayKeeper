package com.planit.holiday.country.application;

import com.planit.holiday.country.domain.CountryRepository;
import com.planit.holiday.country.domain.HolidayClient;
import com.planit.holiday.country.domain.Country;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final HolidayClient holidayClient;

    @Transactional
    public void save() {
        List<CountryResponse> availableCountries = holidayClient.getAvailableCountries();

        List<Country> countries = availableCountries.stream()
                .map(i -> new Country(i.getCountryCode(), i.getName()))
                .toList();
        //벌크 연산으로 변경 가능하다.
        countryRepository.saveAll(countries);
    }
}
