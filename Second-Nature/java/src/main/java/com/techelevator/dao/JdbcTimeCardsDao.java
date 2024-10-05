package com.techelevator.dao;

import com.techelevator.model.TimeCard.ArchiveTimeCardDto;
import com.techelevator.model.TimeCard.CreateTimeCardDto;
import com.techelevator.model.TimeCard.TimeCards;
import com.techelevator.model.TimeCard.UpdateTimeCardDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

public class JdbcTimeCardsDao implements TimeCardsDao{

    private JdbcTemplate template;

    public JdbcTimeCardsDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    @Override
    public TimeCards createTimeCards(CreateTimeCardDto timeCardDto, int userId) {
        Integer timeCardId = -1;

        String sql = "INSERT INTO time_cards (user_id, hour_type, date_time) VALUES (?, ?, ?) RETURNING time_card_id";

        try {
            timeCardId = template.queryForObject(sql, int.class,
                 timeCardDto.getUserId(),
                 timeCardDto.getHourType(),
                 timeCardDto.getDateTime() );
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC TimeCards DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC TimeCards DAO] Error creating new time card.");
        }

        return getTimeCardByUserId(timeCardId);
    }

    @Override
    public TimeCards getTimeCardByUserId(int userId) {
        TimeCards timeCard = new TimeCards();
        String sql = "SELECT * FROM time_cards WHERE user_id = ?";

        try {
            SqlRowSet results = template.queryForRowSet(sql, userId);
            if (results.next()) {
                timeCard = mapRowToTimeCard(results);
            }
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Equipment DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Equipment DAO] Cannot get time card with user ID: " + userId);
        }

        return timeCard;
    }

    @Override
    public TimeCards getTimeCardById(int timeCardId) {
        TimeCards timeCard = new TimeCards();
        String sql = "SELECT * FROM time_cards WHERE user_id = ?";

        try {
            SqlRowSet results = template.queryForRowSet(sql, timeCardId);
            if (results.next()) {
                timeCard = mapRowToTimeCard(results);
            }
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Equipment DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Equipment DAO] Cannot get time card with time card ID: " + timeCardId);
        }

        return timeCard;
    }

    @Override
    public TimeCards updateTimeCard(UpdateTimeCardDto updateTimeCardDto, int timeCardId) {
        String sql = "UPDATE time_cards SET time_card_id = ?, hour_type = ?, date_time = ?;";

        try {
            template.update(
                    sql,
                    updateTimeCardDto.getTimeCardId(),
                    updateTimeCardDto.getHourType(),
                    updateTimeCardDto.getDateTime()
            );
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Equipment DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Equipment DAO] Error updating time card ID: " + updateTimeCardDto.getTimeCardId());
        }

        return getTimeCardById(updateTimeCardDto.getTimeCardId());
    }

    @Override
    public void deleteTimeCard(int timeCardId) {
        String sql = "DELETE FROM time_cards WHERE time_card_id = ?";

        try {
            template.update(sql, timeCardId);
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Equipment DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Equipment DAO] Error deleting time card ID: " + timeCardId);
        }
    }

    @Override
    public TimeCards archiveTimeCard(ArchiveTimeCardDto archiveTimeCardDto, int timeCardId) {
        String sql = "UPDATE time_cards SET updated_on_date = ?, updated_by_user_id = ?, is_archived = ?, " +
                "archived_notes = ? WHERE time_card_id = ?;";

        try {
            template.update(sql,
                    timeCardId,
                    archiveTimeCardDto.getIsArchived(),
                    archiveTimeCardDto.getArchivedNotes());
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Equipment DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Equipment DAO] Error archiving time card ID: " + archiveTimeCardDto.getTimeCardId());
        }

        return getTimeCardById(archiveTimeCardDto.getTimeCardId());
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

}
