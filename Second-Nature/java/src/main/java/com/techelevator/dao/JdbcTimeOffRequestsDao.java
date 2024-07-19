package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.TimeOffRequests;
import com.techelevator.model.User;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        timeOffRequests.setRequestReason(rowSet.getString("request_reason"));
        timeOffRequests.setComment(rowSet.getString("comment"));
        timeOffRequests.setReviewDate(convertToLocalDate(rowSet.getDate("review_date")));
        return timeOffRequests;
    }

    public List<TimeOffRequests> getAllTimeOffRequests() {
        List<TimeOffRequests> timeOffRequests = new ArrayList<>();
        String sql = "SELECT * FROM time_off_requests ORDER BY request_date DESC";

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


    @Override
    public List<TimeOffRequests> getAllTimeOffRequestsByUsername(String username) {
       List <TimeOffRequests> timeOffRequests = new ArrayList<>();
        String sql = "SELECT * FROM time_off_requests JOIN users ON time_off_requests.user_id = users.user_id WHERE users.username = ? ORDER BY request_date DESC";

            SqlRowSet results = template.queryForRowSet(sql, username);
            while (results.next()) {
               TimeOffRequests timeOffRequests1  = mapRowToTimeOffRequests(results);
               timeOffRequests.add(timeOffRequests1);
            }

        return timeOffRequests;
    }

    @Override
    public TimeOffRequests createNewTimeOffRequest(TimeOffRequests timeOffRequest,int userId){
        TimeOffRequests timeOffRequests = null;
        String sql = "INSERT INTO time_off_requests (user_id, request_date, start_date, end_date, status, request_reason, comment, review_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING request_id;";
        int newTimeOffRequestId = -1;


        try {
            newTimeOffRequestId = template.queryForObject(sql, Integer.class,
                    userId,
                    timeOffRequest.getRequestDate(),
                    timeOffRequest.getStartDate(),
                    timeOffRequest.getEndDate(),
                    timeOffRequest.getStatus(),
                    timeOffRequest.getRequestReason(),
                    timeOffRequest.getComment(),
                    timeOffRequest.getReviewDate()



            );
        } catch(CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }


        return getTimeOffRequestByRequestId(newTimeOffRequestId);
    }

    public TimeOffRequests getTimeOffRequestByRequestId(int requestId){
        TimeOffRequests timeOffRequests = null;
        String  sql = "SELECT * FROM time_off_requests WHERE request_id = ?";
        try {
            SqlRowSet results = template.queryForRowSet(sql, requestId);

            if(results.next()) {
                timeOffRequests = mapRowToTimeOffRequests(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return timeOffRequests;
    }

    @Override
    public List<TimeOffRequests> getAllTimeOffRequestsByUserId(int id) {
        List <TimeOffRequests> timeOffRequests = new ArrayList<>();
        String sql = "SELECT * FROM time_off_requests JOIN users ON time_off_requests.user_id = users.user_id WHERE users.user_id = ? ORDER BY request_date DESC";

        SqlRowSet results = template.queryForRowSet(sql, id);
        while (results.next()) {
            TimeOffRequests timeOffRequests1  = mapRowToTimeOffRequests(results);
            timeOffRequests.add(timeOffRequests1);
        }

        return timeOffRequests;
    }

    @Override
    public TimeOffRequests updateTimeOffRequestById(int id, TimeOffRequests timeOffRequests){
        TimeOffRequests updatedTimeOffRequest = null;

        String sql = "UPDATE time_off_requests SET status = ?, comment = ?, review_date = ? WHERE request_id = ?";

        try {
            template.update(sql, timeOffRequests.getStatus(), timeOffRequests.getComment(), timeOffRequests.getReviewDate(), id);
            updatedTimeOffRequest = getTimeOffRequestByRequestId(id);
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Unable to update Time Off Request ID: " + id);
        }

        return updatedTimeOffRequest;
    }

    @Override
    public void deleteTimeOffRequest(int id){
        String sql = "DELETE FROM time_off_requests WHERE request_id = ?";

        try {
            template.update(sql, id);
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("Cannot connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Cannot delete Time Off Request ID: " + id);
        }
    }
}
