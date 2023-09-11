package com.example.game.repository;

import com.example.game.model.enitity.CurrencyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyInfoRepository extends JpaRepository<CurrencyInfo, Long> {
}
