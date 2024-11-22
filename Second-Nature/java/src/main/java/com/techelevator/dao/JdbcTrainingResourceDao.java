package com.techelevator.dao;



import com.techelevator.model.TrainingResource.ArchiveTrainingResourceDto;
import com.techelevator.model.TrainingResource.CreateTrainingResourceDTO;
import com.techelevator.model.TrainingResource.TrainingResource;
import com.techelevator.model.TrainingResource.UpdateTrainingResourceDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JdbcTrainingResourceDao implements TrainingResourceDao{
    private JdbcTemplate template;
    public JdbcTrainingResourceDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }



    public TrainingResource createTrainingResource(CreateTrainingResourceDTO createTrainingResourceDTO, int userId) {

        String sql = "INSERT INTO training_resource (title, category, content, resource_source, entered_on_date, entered_by_user_id, is_archived) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING training_resource_id;";

        int trainingResourceId = -1;

        try {
            trainingResourceId = template.queryForObject(
                    sql,
                    int.class,
                    createTrainingResourceDTO.getTitle(),
                    createTrainingResourceDTO.getCategory(),
                    createTrainingResourceDTO.getContent(),
                    createTrainingResourceDTO.getResourceSource(),
                    new Date(),
                    userId,
                    false

            );
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC Training Resource DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC Training Resource DAO] Unable to create a new Training Resource.");
        }



        return getTrainingResourceById(trainingResourceId);
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

    public List<TrainingResource> getListOfTrainingResources() {
        List<TrainingResource> trainingResources = new ArrayList<>();
        String sql = "SELECT * FROM training_resource;";

        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()){
                trainingResources.add(mapRowToTrainingResource(results));
            }
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Training Resource DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Training Resource DAO] Cannot get a list of all Training Resources");
        }

        return trainingResources;
    }

    public TrainingResource updateTrainingResource(UpdateTrainingResourceDto updateTrainingResourceDto, int userId) {
        String sql = "UPDATE training_resource SET title = ?, category = ?, content = ?, resource_source = ?, updated_by_user_id = ?, updated_on_date = ? WHERE training_resource_id = ?;";

        try {
            template.update(
                    sql,
                    updateTrainingResourceDto.getTitle(),
                    updateTrainingResourceDto.getCategory(),
                    updateTrainingResourceDto.getContent(),
                    updateTrainingResourceDto.getResourceSource(),
                    userId,
                    new Date(),
                    updateTrainingResourceDto.getTrainingResourceId()

            );
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Training Resource DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Training Resource DAO] Error updating Training Resource ID: " + updateTrainingResourceDto.getTrainingResourceId());
        }

        return getTrainingResourceById(updateTrainingResourceDto.getTrainingResourceId());
    }

    public TrainingResource archiveTrainingResource(ArchiveTrainingResourceDto archiveTrainingResourceDto, int userId) {
        String sql = "UPDATE training_resource SET updated_by_user_id = ?, updated_on_date = ?, is_archived = ?, archived_notes = ? WHERE training_resource_id = ?;";

        try {
            template.update(
                    sql,
                    userId,
                    new Date(),
                    true,
                    archiveTrainingResourceDto.getArchivedNotes(),
                    archiveTrainingResourceDto.getTrainingResourceId()
            );
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Training Resource DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Training Resource DAO] Error archiving Training Resource ID: " + archiveTrainingResourceDto.getTrainingResourceId());
        }

        return getTrainingResourceById(archiveTrainingResourceDto.getTrainingResourceId());
    }

    public void deleteTrainingResource(int trainingResourceId) {
        String sql = "DELETE FROM training_resource WHERE training_resource_id = ?;";

        try {
            template.update(sql, trainingResourceId);
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Training Resource DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Equipment DAO] Error deleting Training Resource ID: " + trainingResourceId);
        }
    }

    private TrainingResource mapRowToTrainingResource(SqlRowSet results) {
        TrainingResource trainingResource = new TrainingResource();
        trainingResource.setTrainingResourceId(results.getInt("training_resource_id"));
        trainingResource.setTitle(results.getString("title"));
        trainingResource.setCategory(results.getInt("category"));
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
