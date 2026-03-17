package com.example.turistguide.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TouristRepositorySQL {
    private final JdbcTemplate jdbcTemplate;

    public TouristRepositorySQL(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}


}
