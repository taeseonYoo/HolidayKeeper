package com.planit.holiday.country.domain;

import com.planit.holiday.country.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,String> {
}
