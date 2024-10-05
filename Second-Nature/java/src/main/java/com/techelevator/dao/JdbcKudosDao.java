package com.techelevator.dao;

import com.techelevator.model.Kudos.Kudos;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

@Component
public class JdbcKudosDao implements KudosDao{
    private JdbcTemplate template;

    public JdbcKudosDao(DataSource ds){
        template = new JdbcTemplate(ds);
    }

    @Override
    public Kudos insertKudos(Kudos kudos) {
        String sql = "INSERT INTO kudos (giver_user_id, receiver_user_id, date, title, notes, is_archived, archived_notes) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING kudos_id;";
        int newKudosId = -1;

        try {
            newKudosId = template.queryForObject(
                    sql,
                    int.class,
                    kudos.getGiverUserId(),
                    kudos.getReceiverUserId(),
                    kudos.getDate(),
                    kudos.getTitle(),
                    kudos.getNotes(),
                    kudos.isArchived(),
                    kudos.getArchivedNotes()
            );
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC Kudos DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC Kudos DAO] Unable to insert kudos.");
        }

        return getKudosById(newKudosId);
    }

    @Override
    public Kudos updateKudos(Kudos kudos) {
        String sql = "UPDATE kudos SET giver_user_id = ?, receiver_user_id = ?, date = ?, title = ?, notes = ? WHERE kudos_id = ?;";

        try {
            template.update(
                    sql,
                    kudos.getGiverUserId(),
                    kudos.getReceiverUserId(),
                    kudos.getDate(),
                    kudos.getTitle(),
                    kudos.getNotes(),
                    kudos.getKudosId()
            );
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC Kudos DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC Kudos DAO] Unable to update kudos by title: " + kudos.getTitle());
        }

        return getKudosById(kudos.getKudosId());
    }

    @Override
    public List<Kudos> getAllKudos() {
        List<Kudos> allKudos = new ArrayList<>();
        String sql = "SELECT * FROM kudos ORDER BY date DESC;";

        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()){
                allKudos.add(mapRowToKudos(results));
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC Kudos DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC Kudos DAO] Unable to get all kudos.");
        }

        return allKudos;
    }

    @Override
    public List<Kudos> getKudosByReceiverUserId(int receiverUserId) {
        List<Kudos> allKudos = new ArrayList<>();
        String sql = "SELECT * FROM kudos WHERE receiver_user_id = ? ORDER BY date DESC;";

        try  {
            SqlRowSet results = template.queryForRowSet(sql, receiverUserId);

            while (results.next()){
                allKudos.add(mapRowToKudos(results));
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC Kudos DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC Kudos DAO] Unable to get all kudos by receiver ID: " + receiverUserId);
        }

        return allKudos;
    }

    @Override
    public List<Kudos> getKudosByGiverId(int giverUserId) {
        List<Kudos> allKudos = new ArrayList<>();
        String sql = "SELECT * FROM kudos WHERE giver_user_id = ? ORDER BY date DESC;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, giverUserId);

            while (results.next()){
                allKudos.add(mapRowToKudos(results));
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC Kudos DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC Kudos DAO] Unable to get all kudos by giver ID: " + giverUserId);
        }

        return allKudos;
    }

    @Override
    public Kudos getKudosById(int id) {
        Kudos kudos = null;
        String sql = "SELECT * FROM kudos WHERE kudos_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, id);
            if (results.next()){
                kudos = mapRowToKudos(results);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC Kudos DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC Kudos DAO] Unable to get kudos by ID: " + id);
        }

        return kudos;
    }

    @Override
    public void deleteKudos(Kudos kudos) {
        String sql = "DELETE FROM kudos WHERE kudos_id = ?;";

        try {
            template.update(sql, kudos.getKudosId());
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC Kudos DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC Kudos DAO] Unable to delete kudos by ID: " + kudos.getKudosId());
        }
    }

    @Override
    public Kudos archiveKudos(Kudos kudos) {
        String sql = "UPDATE kudos SET is_archived = ?, archived_notes = ? WHERE kudos_id = ?;";

        try {
            template.update(
                    sql,
                    true,
                    kudos.getArchivedNotes(),
                    kudos.getKudosId()
            );
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC Kudos DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC Kudos DAO] Unable to archive kudos by ID: " + kudos.getKudosId());
        }

        return getKudosById(kudos.getKudosId());
    }


    private Kudos mapRowToKudos(SqlRowSet results){
        Kudos kudos = new Kudos();
        kudos.setKudosId(results.getInt("kudos_id"));
        kudos.setGiverUserId(results.getInt("giver_user_id"));
        kudos.setReceiverUserId(results.getInt("receiver_user_id"));
        kudos.setDate(results.getDate("date"));
        kudos.setTitle(results.getString("title"));
        kudos.setNotes(results.getString("notes"));
        kudos.setArchived(results.getBoolean("is_archived"));
        kudos.setArchivedNotes(results.getString("archived_notes"));
        return kudos;
    }



    public Kudos getKudosByKudosId(int kudosId){
        Kudos kudos = null;
        String  sql = "SELECT * FROM kudos WHERE kudos_id = ?";
        try {
            SqlRowSet results = template.queryForRowSet(sql, kudosId);

            if(results.next()) {
                kudos = mapRowToKudos(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return kudos;
    }

}