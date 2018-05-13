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
import java.sql.Types;
import java.util.Collection;

@Component
public class CustomerSentimentIndexRepository {
    private static final Logger logger = LoggerFactory.getLogger(CustomerSentimentIndexRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerSentimentIndexRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    public int insertAll(Collection<TimeSeriesObservation> items) {
        logger.info("Truncating table 'customer_sentiment_index'");
        jdbcTemplate.execute("TRUNCATE TABLE customer_sentiment_index");
        logger.info("Table truncation finished");

        int count = 0;
        String query = "INSERT INTO customer_sentiment_index(obs_date, value) VALUES(?, ?)";

        for(TimeSeriesObservation item : items) {
            count += jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setDate(1, new Date(item.getDate().getTimeInMillis()));
                if(item.getValue() == null) {
                    ps.setNull(2, Types.DECIMAL);
                } else {
                    ps.setDouble(2, item.getValue());
                }

                return ps;
            });
        }

        return count;
    }

    @Transactional
    public int insertIncremental(Collection<TimeSeriesObservation> items) {
        int count = 0;
        String query = "INSERT INTO customer_sentiment_index(obs_date, value) VALUES(?, ?)";
        String verificationQuery = "SELECT COUNT(1) FROM customer_sentiment_index WHERE obs_date = ?";

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
