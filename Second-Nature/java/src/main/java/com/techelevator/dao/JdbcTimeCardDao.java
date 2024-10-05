package com.techelevator.dao;

import com.techelevator.model.CreateTimeCardDto;
import com.techelevator.model.Equipment;
import com.techelevator.model.TimeCards;
import com.techelevator.model.UpdateTimeCardDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JdbcTimeCardDao implements TimeCardDao {
    private JdbcTemplate template;

    public JdbcTimeCardDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    public TimeCards createTimeCard(CreateTimeCardDto createTimeCardDto, int userId, Timestamp timestamp) {
        int timeCardId = -1;
        String sql = "INSERT INTO time_cards (user_id, hour_type, date_time, updated_on_date) VALUES (?,?,?,?) RETURNING time_card_id;";
        timeCardId = template.queryForObject(
                sql,
                int.class,
                userId,
                createTimeCardDto.getHourType(),
                timestamp,
                new Date()

        );
        return getTimeCardById(timeCardId);
    }


    public TimeCards getTimeCardById(int timeCardId) {
        TimeCards timeCard = new TimeCards();
        String sql = "SELECT * FROM time_cards WHERE time_card_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, timeCardId);
            if (results.next()) {
                timeCard = mapRowToTimeCard(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Time Card DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Time Card DAO] Cannot get time card with ID: " + timeCardId);
        }

        return timeCard;
    }


    private TimeCards mapRowToTimeCard(SqlRowSet results) {
        TimeCards timeCard = new TimeCards();
        timeCard.setTimeCardId(results.getInt("time_card_id"));
        timeCard.setUserId(results.getInt("user_id"));
        timeCard.setHourType(results.getString("hour_type"));
        timeCard.setDateTime(results.getTimestamp("date_time"));
        timeCard.setUpdatedOnDate(results.getDate("updated_on_date"));
        timeCard.setUpdatedByUserId(results.getInt("updated_by_user_id"));

        timeCard.setIsArchived(results.getBoolean("is_archived"));
        timeCard.setArchivedNotes(results.getString("archived_notes"));
        return timeCard;
    }

    public List<TimeCards> getTimeCardsByUserId(int userId) {
        List<TimeCards> timeCards = new ArrayList<>();
        String sql = "SELECT * FROM time_cards WHERE user_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, userId);
            while (results.next()) {
                TimeCards timeCard = new TimeCards();
                timeCard.setTimeCardId(results.getInt("time_card_id"));
                timeCard.setUserId(results.getInt("user_id"));
                timeCard.setHourType(results.getString("hour_type"));
                timeCard.setDateTime(results.getTimestamp("date_time"));
                timeCard.setUpdatedOnDate(results.getDate("updated_on_date"));
                timeCard.setUpdatedByUserId(results.getInt("user_id"));
                timeCard.setIsArchived(results.getBoolean("is_archived"));
                timeCard.setArchivedNotes(results.getString("archived_notes"));
                timeCards.add(timeCard);


            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Time Card DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Time Card DAO] Cannot get time card with ID: " + userId);
        }

        return timeCards;
    }

    @Override
    public TimeCards updateTimeCard(UpdateTimeCardDto updateTimeCardDto, int userId, Timestamp timestamp) {
        String sql = "UPDATE time_cards SET time_card_id = ?, hour_type = ?, date_time = ?, updated_on_date = ?, updated_by_user_id = ? WHERE time_card_id = ?;";

        try {
            template.update(
                    sql,
                    updateTimeCardDto.getTimeCardId(),
                    updateTimeCardDto.getHourType(),
                    timestamp,
                    new Date(),
                    userId,
                    updateTimeCardDto.getTimeCardId()
            );
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Equipment DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Equipment DAO] Error updating equipment ID: " + updateTimeCardDto.getTimeCardId());
        }

        return getTimeCardById(updateTimeCardDto.getTimeCardId());
    }
}
