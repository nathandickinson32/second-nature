package com.techelevator.dao;


import com.techelevator.model.TrainingResource.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcCategoryDao implements CategoryDao{
    private JdbcTemplate template;

    public JdbcCategoryDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    @Override
    public Category createCategory(Category category){
        int categoryId = -1;
        String sql = "INSERT INTO category (name) VALUES (?) RETURNING category_id;";
        try {
            categoryId = template.queryForObject(sql, int.class, category.getName());
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Type DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Type DAO] Error creating new category.");
        }

        return getCategoryByCategoryId(categoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> allCategories = new ArrayList<>();
        String sql = "SELECT * FROM category;";

        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()){
                allCategories.add(mapRowToType(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Type DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Type DAO] Error getting all categories.");
        }

        return allCategories;
    }

    @Override
    public Category getCategoryByCategoryId(int categoryId) {
        Category category = null;
        String sql = "SELECT * FROM category WHERE category_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, categoryId);
            if (results.next()){
                category = mapRowToType(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Type DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Type DAO] Error getting category by ID: " + categoryId);
        }

        return category;
    }

    @Override
    public Category updateCategory(Category category) {
        String sql = "UPDATE category SET name = ? WHERE category_id = ?;";

        try {
            template.update(sql, category.getName(), category.getCategoryId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Type DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Type DAO] Error updating category by ID: " + category.getCategoryId());
        }

        return getCategoryByCategoryId(category.getCategoryId());
    }

    @Override
    public void deleteCategory(int categoryId) {
        String sql = "DELETE FROM category WHERE category_id = ?;";

        try {
            template.update(sql, categoryId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Type DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Type DAO] Error deleting category by ID: " + categoryId);
        }
    }

    private Category mapRowToType(SqlRowSet results){
        Category category = new Category();
        category.setCategoryId(results.getInt("category_id"));
        category.setName(results.getString("name"));
        return category;
    }
}
