package com.example.game.controller;

import com.example.game.exception.BadRequestException;
import com.example.game.model.dto.CountryResponse;
import com.example.game.model.dto.OneRegionCountryResponse;
import com.example.game.service.CountryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/country")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CountryController {

    CountryService countryService;

    @GetMapping("/by_name")
    public ResponseEntity<CountryResponse> findByName(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "capital_name", required = false) String capitalName,
            @RequestParam(name = "currency", required = false) String currency) {
        if (name != null && capitalName != null && currency != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(countryService.findCountry(name, capitalName, currency), HttpStatus.OK);
    }

    @GetMapping("/by_region")
    public List<OneRegionCountryResponse> getAllCountryInONeRegion(@RequestParam String region) {
        return countryService.findAllByRegion(region);
    }

}
