package com.example.game.model.dto;

import com.example.game.model.enitity.CurrencyInfo;
import com.example.game.model.enitity.Language;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class CountryResponse {

    Long id;
    String name;
    String officialName;
    List<CurrencyInfo> currencyInfos;
    String capital;
    Language     language;
    List<String> borders;
    Double population;
    List<String> timeZone;
}
