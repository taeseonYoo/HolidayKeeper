package com.planit.keeper.country.infra;

import com.planit.keeper.country.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,String> {
}
