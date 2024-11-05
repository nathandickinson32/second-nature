package com.techelevator.dao;

import com.techelevator.model.TimeCard.ArchiveTimeCardDto;
import com.techelevator.model.TimeCard.CreateTimeCardDto;
import com.techelevator.model.TimeCard.TimeCards;
import com.techelevator.model.TimeCard.UpdateTimeCardDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JdbcTimeCardsDao implements TimeCardsDao {
    private JdbcTemplate template;

    public JdbcTimeCardsDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    private Timestamp roundToNearestQuarterHour(Timestamp timestamp) {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        int minutes = localDateTime.getMinute();

        int roundedMinutes;

        if (minutes % 15 < 8 ) {
            roundedMinutes = minutes - ( minutes%15);

        } else {
            roundedMinutes = minutes + ( 15 - (minutes % 15));
        }
        LocalDateTime roundedDateTime = localDateTime.withMinute(roundedMinutes).withSecond(0).withNano(0);
        return Timestamp.valueOf(roundedDateTime);
    }

    // method for determining clocked in status to either update current time card or create new one.
    // if clocked in update
    // else create new
    // keep method params in mind

//    private TimeCards clockedInStatus(int timeCardId) {
//        TimeCards timeCard = new TimeCards();
//        String sql = "SELECT * FROM time_cards LIMIT 1 ORDER BY time_card_id DESC";
//        try {
//            SqlRowSet results = template.queryForRowSet(sql, timeCardId);
//            if (results.next()) {
//                timeCard = mapRowToTimeCard(results);
//            }
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new CannotGetJdbcConnectionException("[JDBC Time Card DAO] Problem connecting to the database.");
//        } catch (DataIntegrityViolationException e) {
//            throw new DataIntegrityViolationException("[JDBC Time Card DAO] Cannot get time card with ID: " + timeCardId);
//        }
//        if(timeCard.isClockedIn()){
//            updateTimeCard()
//
//        }
//    }

    public TimeCards createTimeCard(int userId, Timestamp timestamp) {
        int timeCardId = -1;
        String sql = "INSERT INTO time_cards (user_id, date_time_in, clocked_in, clock_in_time) VALUES (?,?,?,?) RETURNING time_card_id;";
        timeCardId = template.queryForObject(
                sql,
                int.class,
                userId,
                timestamp,
                true,
                roundToNearestQuarterHour(timestamp)

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



    public List<TimeCards> getTimeCardsByUserId(int userId) {
        List<TimeCards> timeCards = new ArrayList<>();
        String sql = "SELECT * FROM time_cards WHERE user_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, userId);
            while (results.next()) {
                TimeCards timeCard = new TimeCards();
                timeCard.setTimeCardId(results.getInt("time_card_id"));
                timeCard.setUserId(results.getInt("user_id"));
                timeCard.setDateTimeIn(results.getTimestamp("date_time_in"));
                timeCard.setDateTimeOut(results.getTimestamp("date_time_out"));
                timeCard.setClockedIn(results.getBoolean("clocked_in"));
                timeCard.setTotalMinutesWorked(results.getInt("total_minutes_worked"));
                timeCard.setClockInTime(results.getTimestamp("clock_in_time"));
                timeCard.setClockOutTime(results.getTimestamp("clock_out_time"));
                timeCard.setUpdatedOnDate(results.getDate("updated_on_date"));
                timeCard.setUpdatedByUserId(results.getInt("updated_by_user_id"));
                timeCard.setArchived(results.getBoolean("is_archived"));
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
        String sql = "UPDATE time_cards SET date_time_out = ?, clocked_in = ?, total_minutes_worked = ?,clock_out_time = ?, updated_on_date = ?, updated_by_user_id = ? WHERE time_card_id = ?;";

        try {
            Timestamp clockInTime = updateTimeCardDto.getClockInTime();
            Timestamp roundedTimeStamp = roundToNearestQuarterHour(timestamp);
            int totalMinutesWorked = calculateMinutesWorked(clockInTime,roundedTimeStamp);
            template.update(
                    sql,
                    timestamp,
                    false,
                    totalMinutesWorked,
                    roundedTimeStamp,
                    new Date(),
                    userId,
                    updateTimeCardDto.getTimeCardId()
            );
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Time Card DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Time Card DAO] Error updating time card ID: " + updateTimeCardDto.getTimeCardId());
        }

        return getTimeCardById(updateTimeCardDto.getTimeCardId());
    }

    public TimeCards archiveTimeCard(ArchiveTimeCardDto archiveTimeCardDto, int userId) {
        String sql = "UPDATE time_cards SET updated_on_date = ?, updated_by_user_id = ?, is_archived = ?, " +
                "archived_notes = ? WHERE time_card_id = ?;";

        try {
            template.update(sql,
                    new Date(),
                    userId,
                    archiveTimeCardDto.getIsArchived(),
                    archiveTimeCardDto.getArchivedNotes(),
                    archiveTimeCardDto.getTimeCardId())
                    ;
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
        timeCard.setDateTimeIn(results.getTimestamp("date_time_in"));
        timeCard.setDateTimeOut(results.getTimestamp("date_time_out"));
        timeCard.setClockedIn(results.getBoolean("clocked_in"));
        timeCard.setTotalMinutesWorked(results.getInt("total_minutes_worked"));
        timeCard.setClockInTime(results.getTimestamp("clock_in_time"));
        timeCard.setClockOutTime(results.getTimestamp("clock_out_time"));
        timeCard.setUpdatedOnDate(results.getDate("updated_on_date"));
        timeCard.setUpdatedByUserId(results.getInt("updated_by_user_id"));
        timeCard.setArchived(results.getBoolean("is_archived"));
        timeCard.setArchivedNotes(results.getString("archived_notes"));
        return timeCard;
    }

    public static int calculateMinutesWorked(Timestamp startTimestamp, Timestamp endTimestamp) {
        // Convert Timestamp to LocalDateTime
        LocalDateTime start = startTimestamp.toLocalDateTime();
        LocalDateTime end = endTimestamp.toLocalDateTime();

        // Calculate the duration between the two LocalDateTime instances
        Duration duration = Duration.between(start, end);

        // Convert duration to total minutes
        long totalMinutes = duration.toMinutes();

        return (int) totalMinutes; // Cast to int
    }
}
