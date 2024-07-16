package com.techelevator.dao;

import com.techelevator.model.TimeOffRequests;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

@Component
public class JdbcTimeOffRequestsDao implements TimeOffRequestsDao {
    private JdbcTemplate template;

    public JdbcTimeOffRequestsDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    private TimeOffRequests mapRowToTimeOffRequests(SqlRowSet rowSet) {
        TimeOffRequests timeOffRequests = new TimeOffRequests();
        timeOffRequests.setRequestId(rowSet.getInt("request_id"));
        timeOffRequests.setUserId(rowSet.getInt("user_id"));
        timeOffRequests.setRequestDate(convertToLocalDate(rowSet.getDate("request_date")));
        timeOffRequests.setStartDate(convertToLocalDate(rowSet.getDate("start_date")));
        timeOffRequests.setEndDate(convertToLocalDate(rowSet.getDate("end_date")));
        timeOffRequests.setStatus(rowSet.getString("status"));
        timeOffRequests.setStatus(rowSet.getString("request_reason"));
        timeOffRequests.setStatus(rowSet.getString("comment"));
        timeOffRequests.setReviewDate(convertToLocalDate(rowSet.getDate("review_date")));
        return timeOffRequests;
    }

    public List<TimeOffRequests> getAllTimeOffRequests() {
        List<TimeOffRequests> timeOffRequests = new ArrayList<>();
        String sql = "SELECT * FROM time_off_requests";

        SqlRowSet results = template.queryForRowSet(sql);
        while (results.next()) {
            TimeOffRequests timeOffRequests1 = mapRowToTimeOffRequests(results);
            timeOffRequests.add(timeOffRequests1);
        }


        return timeOffRequests;
    }

    private LocalDate convertToLocalDate(Date date) {
        return date != null ? date.toLocalDate() : null;
    }

}
