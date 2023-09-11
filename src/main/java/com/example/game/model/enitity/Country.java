package com.example.game.model.enitity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "countries")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String capital;
    Double population;
    String officialName;
    //    @ElementCollection
//    Map<String, String> language;
    @ElementCollection
    List<String> borders;
    @ElementCollection
    List<String> timeZone;

    @OneToOne
    Language language;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")
    List<CurrencyInfo> currencyInfos;
}
