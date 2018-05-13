package com.fundingcircle.repository;

import com.fundingcircle.data.TimeSeriesObservation;
import com.fundingcircle.data.UnemploymentRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@Component
public class UnemploymentRateRepository {
    private static final Logger logger = LoggerFactory.getLogger(UnemploymentRateRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UnemploymentRateRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    public int insertAll(Collection<TimeSeriesObservation> items) {
        logger.info("Truncating table 'unemployment_rate'");
        jdbcTemplate.execute("TRUNCATE TABLE unemployment_rate");
        logger.info("Table truncation finished");

        int count = 0;
        String query = "INSERT INTO unemployment_rate(obs_date, value) VALUES(?, ?)";

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
        String query = "INSERT INTO unemployment_rate(obs_date, value) VALUES(?, ?)";
        String verificationQuery = "SELECT COUNT(1) FROM unemployment_rate WHERE obs_date = ?";

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

    @Transactional
    public Collection<UnemploymentRate> getUnemploymentRateAverages() {
        String query = "select year(obs_date) as year, sum(value) as total, avg(value) as average from unemployment_rate where year(obs_date) >= 1980 and year(obs_date) <= 2015 group by year(obs_date)";

        Collection<UnemploymentRate> rates = new Vector<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        rows.forEach(item -> {
            UnemploymentRate data = new UnemploymentRate();
            data.setYear((Integer) item.get("year")).setAverageRate((BigDecimal) item.get("average"));
            rates.add(data);
        });
        return rates;
    }
}
