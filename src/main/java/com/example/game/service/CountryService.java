package com.example.game.service;

import com.example.game.model.dto.CountryResponse;
import com.example.game.model.dto.OneRegionCountryResponse;
import com.example.game.model.dto.mapper.CountryMapper;
import com.example.game.model.enitity.Country;
import com.example.game.model.enitity.CurrencyInfo;
import com.example.game.repository.CountryRepository;
import com.example.game.repository.CurrencyInfoRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.print.DocFlavor;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CountryService {

    Parser parser;
    CountryMapper countryMapper;
    CountryRepository countryRepository;

    public CountryResponse findCountry(String name, String capitalName, String currency) {

        if (name != null) {
            return findByName(name);
        } else if (capitalName != null) {
            return findByCapitalName(capitalName);
        } else if (currency != null) {
            return findByCurrency(currency);
        } else {
            return null;
        }
    }

    public CountryResponse findByName(String name) {
        List<Country> countries = parser.getCountryFromJson("https://restcountries.com/v3.1/name/" + name);
        return getCountryResponse(countries);
    }

    public CountryResponse findByCapitalName(String capitalName) {
        List<Country> countries = parser.getCountryFromJson("https://restcountries.com/v3.1/capital/" + capitalName);
        return getCountryResponse(countries);
    }

    public CountryResponse findByCurrency(String currency) {
        List<Country> countries = parser.getCountryFromJson("https://restcountries.com/v3.1/currency/" + currency);
        return getCountryResponse(countries);
    }

    public List<OneRegionCountryResponse> findAllByRegion(String region) {
        List<Country> oneRegionCountry = parser.getCountryFromJson("https://restcountries.com/v3.1/region/" + region);
        countryRepository.saveAll(oneRegionCountry);
        return countryMapper.mapToResponse(oneRegionCountry);
    }

    private CountryResponse getCountryResponse(List<Country> countries) {
        List<CurrencyInfo> currencyInfos = countries.get(0).getCurrencyInfos();
        currencyInfos.forEach(currencyInfo -> currencyInfo.setCountry(countries.get(0)));
        countries.get(0).setCurrencyInfos(currencyInfos);
        countryRepository.save(countries.get(0));
        return countryMapper.mapToResponse(countries.get(0));
    }

}
