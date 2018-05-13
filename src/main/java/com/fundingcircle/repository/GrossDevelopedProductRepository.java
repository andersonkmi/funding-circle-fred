package com.fundingcircle.repository;

import com.fundingcircle.data.TimeSeriesObservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Collection;

@Component
public class GrossDevelopedProductRepository {
    private static final Logger logger = LoggerFactory.getLogger(GrossDevelopedProductRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GrossDevelopedProductRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    public int insertAll(Collection<TimeSeriesObservation> items) {
        logger.info("Truncating table 'gross_domestic_product'");
        jdbcTemplate.execute("TRUNCATE TABLE gross_domestic_product");
        logger.info("Table truncation finished");

        int count = 0;
        String query = "INSERT INTO gross_domestic_product(obs_date, value) VALUES(?, ?)";

        for(TimeSeriesObservation item : items) {
            count += jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setDate(1, new Date(item.getDate().getTimeInMillis()));
                ps.setDouble(2, item.getValue());
                return ps;
            });
        }

        return count;
    }

    @Transactional
    public int insertIncremental(Collection<TimeSeriesObservation> items) {
        int count = 0;
        String query = "INSERT INTO gross_domestic_product(obs_date, value) VALUES(?, ?)";
        String verificationQuery = "SELECT COUNT(1) FROM gross_domestic_product WHERE obs_date = ?";

        for(TimeSeriesObservation item : items) {
            int countItems = jdbcTemplate.queryForObject(verificationQuery, new Object[] { new Date(item.getDate().getTimeInMillis()) }, Integer.class);
            if(countItems == 0) {
                count += jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setDate(1, new Date(item.getDate().getTimeInMillis()));
                    ps.setDouble(2, item.getValue());
                    return ps;
                });
            }
        }

        return count;
    }
}
