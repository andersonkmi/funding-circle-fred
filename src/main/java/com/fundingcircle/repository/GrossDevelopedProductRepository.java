package com.fundingcircle.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class GrossDevelopedProductRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GrossDevelopedProductRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void truncate() {
        jdbcTemplate.execute("TRUNCATE TABLE gross_domestic_product");
    }
}
