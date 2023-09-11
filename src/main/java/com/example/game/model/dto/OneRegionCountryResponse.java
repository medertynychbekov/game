package com.example.game.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
//@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OneRegionCountryResponse {
    List<CountryResponse> countryResponse;
    long counter;
}
