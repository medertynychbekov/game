package com.example.game.model.dto.mapper;

import com.example.game.model.dto.CountryResponse;
import com.example.game.model.dto.OneRegionCountryResponse;
import com.example.game.model.enitity.Country;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryMapper {

    public CountryResponse mapToResponse(Country country) {
        return CountryResponse.builder()
                .id(country.getId())
                .name(country.getName())
                .officialName(country.getOfficialName())
//                .currencyInfos(country.getCurrencyInfos())
                .capital(country.getCapital())
                .language(country.getLanguage())
                .borders(country.getBorders())
                .population(country.getPopulation())
                .timeZone(country.getTimeZone())
                .build();
    }

    public List<OneRegionCountryResponse> mapToResponse(List<Country> countries) {
        List<OneRegionCountryResponse> oneRegionCountryResponses = new ArrayList<>();

        OneRegionCountryResponse oneRegionCountryResponse = new OneRegionCountryResponse();
        oneRegionCountryResponse.setCountryResponse(countries.stream().map(this::mapToResponse).toList());
        oneRegionCountryResponse.setCounter(3);

        oneRegionCountryResponses.add(oneRegionCountryResponse);
        return oneRegionCountryResponses;
    }
}
