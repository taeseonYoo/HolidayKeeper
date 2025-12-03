package com.planit.keeper.country.application;

import com.planit.keeper.country.infra.CountryRepository;
import com.planit.keeper.country.domain.CountryClient;
import com.planit.keeper.country.domain.Country;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final CountryClient countryClient;

    @Transactional
    public List<Country> save() {
        List<CountryResponse> availableCountries = countryClient.getAvailableCountries();

        List<Country> countries = availableCountries.stream()
                .map(i -> new Country(i.getCountryCode(), i.getName()))
                .toList();
        return countryRepository.saveAll(countries);
    }

    @Transactional(readOnly = true)
    public Country findCountryOrThrow(String countryCode) {
        return countryRepository.findById(countryCode).orElseThrow(() -> new RuntimeException(""));
    }
}
