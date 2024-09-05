package com.techelevator.dao;

import com.techelevator.model.Type;
import com.techelevator.model.TypeDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTypeDao implements TypeDao{

    private JdbcTemplate template;

    public JdbcTypeDao(DataSource ds){
        template = new JdbcTemplate(ds);
    }

    @Override
    public Type createType(TypeDto typeDto) {
        int typeId = -1;
        String sql = "INSERT INTO type (name) VALUES (?) RETURNING type_id;";

        try {
            typeId = template.queryForObject(sql, int.class, typeDto.getName());
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Type DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Type DAO] Error creating new type.");
        }

        return getTypeByTypeId(typeId);
    }

    @Override
    public List<Type> getAllTypes() {
        List<Type> allTypes = new ArrayList<>();
        String sql = "SELECT * FROM type;";

        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()){
                allTypes.add(mapRowToType(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Type DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Type DAO] Error getting all types.");
        }

        return allTypes;
    }

    @Override
    public Type getTypeByTypeId(int typeId) {
        Type type = null;
        String sql = "SELECT * FROM type WHERE type_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, typeId);
            if (results.next()){
                type = mapRowToType(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Type DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Type DAO] Error getting type by ID: " + typeId);
        }

        return type;
    }

    @Override
    public Type updateType(Type type) {
        String sql = "UPDATE type SET name = ? WHERE type_id = ?;";

        try {
            template.update(sql, type.getName(), type.getTypeId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Type DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Type DAO] Error updating type by ID: " + type.getTypeId());
        }

        return getTypeByTypeId(type.getTypeId());
    }

    @Override
    public void deleteType(int typeId) {
        String sql = "DELETE FROM type WHERE type_id = ?;";

        try {
            template.update(sql, typeId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Type DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Type DAO] Error deleting type by ID: " + typeId);
        }
    }

    private Type mapRowToType(SqlRowSet results){
        Type type = new Type();
        type.setTypeId(results.getInt("type_id"));
        type.setName(results.getString("name"));
        return type;
    }
}
