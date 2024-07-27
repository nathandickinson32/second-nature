package com.techelevator.dao;


import com.techelevator.model.Kudos;
import com.techelevator.model.ProfessionalCheckIn;
import com.techelevator.model.TimeOffRequests;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProfessionalCheckInDao implements ProfessionalCheckInDao{
    private JdbcTemplate template;

    public JdbcProfessionalCheckInDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }
    private LocalDate convertToLocalDate(Date date) {
        return date != null ? date.toLocalDate() : null;
    }
    private ProfessionalCheckIn mapRowToProfessionalCheckIn(SqlRowSet rowSet) {
        ProfessionalCheckIn professionalCheckIn = new ProfessionalCheckIn();
       professionalCheckIn.setCheckInId(rowSet.getInt("check_in_id"));
       professionalCheckIn.setManagerId(rowSet.getInt("manager_id"));
       professionalCheckIn.setEmployeeId(rowSet.getInt("employee_id"));
       professionalCheckIn.setNotes(rowSet.getString("notes"));
       professionalCheckIn.setDate(convertToLocalDate(rowSet.getDate("date")));
       return professionalCheckIn;
    }

    public List<ProfessionalCheckIn> getAllProfessionalCheckIns() {
        List<ProfessionalCheckIn> professionalCheckIns = new ArrayList<>();
        String sql = "SELECT * FROM professional_check_in ORDER BY date DESC, check_in_id DESC";

        SqlRowSet results = template.queryForRowSet(sql);
        while (results.next()) {
            ProfessionalCheckIn professionalCheckIns1 = mapRowToProfessionalCheckIn(results);
            professionalCheckIns.add(professionalCheckIns1);
        }


        return professionalCheckIns;
    }



    @Override            // returns check ins by current userId
                        // returns where employeeId === current userId
    public List<ProfessionalCheckIn> getAllProfessionalCheckInsByEmployeeUserId(int id) {
        List <ProfessionalCheckIn> professionalCheckIns = new ArrayList<>();
        String sql = "SELECT * FROM professional_check_in JOIN users ON professional_check_in.employee_id = users.user_id WHERE users.user_id = ? ORDER BY date DESC, check_in_id DESC";

        SqlRowSet results = template.queryForRowSet(sql, id);
        while (results.next()) {
            ProfessionalCheckIn professionalCheckIns1  = mapRowToProfessionalCheckIn(results);
            professionalCheckIns.add(professionalCheckIns1);
        }

        return professionalCheckIns;
    }
    @Override        // returns check ins done by logged in manager
                    // returns where managerId === current userId
    public List<ProfessionalCheckIn> getAllProfessionalCheckInsByManagerUserId(int id) {
        List <ProfessionalCheckIn> professionalCheckIns = new ArrayList<>();
        String sql = "SELECT * FROM professional_check_in JOIN users ON professional_check_in.manager_id = users.user_id WHERE users.user_id = ? ORDER BY date DESC, check_in_id DESC";

        SqlRowSet results = template.queryForRowSet(sql, id);
        while (results.next()) {
            ProfessionalCheckIn professionalCheckIns1  = mapRowToProfessionalCheckIn(results);
            professionalCheckIns.add(professionalCheckIns1);
        }

        return professionalCheckIns;
    }

    // returns check ins by PathVariable employeeId
    public List<ProfessionalCheckIn> getAllProfessionalCheckInsByEmployeeId(int employeeId) {
        List <ProfessionalCheckIn> professionalCheckIns = new ArrayList<>();
        String sql = "SELECT * FROM professional_check_in WHERE employee_id = ? ORDER BY date DESC, check_in_id DESC";

        SqlRowSet results = template.queryForRowSet(sql, employeeId);
        while (results.next()) {
            ProfessionalCheckIn professionalCheckIns1  = mapRowToProfessionalCheckIn(results);
            professionalCheckIns.add(professionalCheckIns1);
        }

        return professionalCheckIns;
    }


    // returns check ins by PathVariable managerId
    public List<ProfessionalCheckIn> getAllProfessionalCheckInsByManagerId(int managerId) {
        List <ProfessionalCheckIn> professionalCheckIns = new ArrayList<>();
        String sql = "SELECT * FROM professional_check_in WHERE manager_id = ? ORDER BY date DESC, check_in_id DESC";

        SqlRowSet results = template.queryForRowSet(sql, managerId);
        while (results.next()) {
            ProfessionalCheckIn professionalCheckIns1  = mapRowToProfessionalCheckIn(results);
            professionalCheckIns.add(professionalCheckIns1);
        }

        return professionalCheckIns;
    }

    // returns single check in by check in id
    public ProfessionalCheckIn getProfessionalCheckInByCheckInId(int checkInId){
        ProfessionalCheckIn professionalCheckIn = null;
        String  sql = "SELECT * FROM professional_check_in WHERE check_in_id = ?";
        try {
            SqlRowSet results = template.queryForRowSet(sql, checkInId);

            if(results.next()) {
                professionalCheckIn = mapRowToProfessionalCheckIn(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return professionalCheckIn;
    }


    @Override
    public ProfessionalCheckIn createNewProfessionalCheckIn(ProfessionalCheckIn professionalCheckIn,int managerId){
        ProfessionalCheckIn professionalCheckIn1 = null;
        String sql = "INSERT INTO professional_check_in (manager_id, employee_id, notes, date) VALUES (?, ?, ?, ?) RETURNING check_in_id;";
        int newProfessionalCheckInId = -1;


        try {
            newProfessionalCheckInId = template.queryForObject(sql, Integer.class,
                    managerId,
                 professionalCheckIn.getEmployeeId(),
                    professionalCheckIn.getNotes(),
                    professionalCheckIn.getDate()



            );
        } catch(CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }


        return getProfessionalCheckInByCheckInId(newProfessionalCheckInId);
    }

    @Override
    public ProfessionalCheckIn updateProfessionalCheckIn(ProfessionalCheckIn professionalCheckIn) {
        String sql = "UPDATE professional_check_in SET manager_id = ?, employee_id = ?, notes = ?, date = ? WHERE check_in_id = ?;";

        try {
            template.update(
                    sql,
                    professionalCheckIn.getManagerId(),
                    professionalCheckIn.getEmployeeId(),
                    professionalCheckIn.getNotes(),
                    professionalCheckIn.getDate(),
                    professionalCheckIn.getCheckInId()

                    );
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC Check In DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC Check In DAO] Unable to update check in: " + professionalCheckIn.getCheckInId());
        }

        return getProfessionalCheckInByCheckInId(professionalCheckIn.getCheckInId());
    }

    public void deleteProfessionalCheckIn(ProfessionalCheckIn professionalCheckIn) {
        String sql = "DELETE FROM professional_check_in WHERE check_in_id = ?;";

        try {
            template.update(sql, professionalCheckIn.getCheckInId());
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC check in DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC check in DAO] Unable to delete check in by ID: " + professionalCheckIn.getCheckInId());
        }
    }
}
