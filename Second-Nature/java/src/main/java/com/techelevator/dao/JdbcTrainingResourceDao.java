package com.techelevator.dao;

import com.techelevator.model.TrainingResource.TrainingResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcTrainingResourceDao {
    private JdbcTemplate template;
    public JdbcTrainingResourceDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }


    public TrainingResource getTrainingResourceById(int trainingResourceId) {
        TrainingResource trainingResource = new TrainingResource();
        String sql = "SELECT * FROM training_resource WHERE training_resource_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, trainingResourceId);
            if (results.next()){
                trainingResource = mapRowToTrainingResource(results);
            }
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Training Resource DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Training Resource DAO] Cannot get Training Resource with ID: " + trainingResourceId);
        }

        return trainingResource;
    }

    private TrainingResource mapRowToTrainingResource(SqlRowSet results) {
        TrainingResource trainingResource = new TrainingResource();
        trainingResource.setTrainingResourceId(results.getInt("training_resource_id"));
        trainingResource.setTitle(results.getString("title"));
        trainingResource.setCategory(results.getString("category"));
        trainingResource.setContent(results.getString("content"));
        trainingResource.setResourceSource(results.getString("resource_source"));
        trainingResource.setEnteredOnDate(results.getTimestamp("entered_on_date"));
        trainingResource.setEnteredByUserId(results.getInt("entered_by_user_id"));
        trainingResource.setUpdatedOnDate(results.getTimestamp("updated_on_date"));

        trainingResource.setUpdatedByUserId(results.getInt("updated_by_user_id"));
        trainingResource.setArchived(results.getBoolean("is_archived"));
        trainingResource.setArchivedNotes(results.getString("archived_notes"));
        return trainingResource;
    }
}
