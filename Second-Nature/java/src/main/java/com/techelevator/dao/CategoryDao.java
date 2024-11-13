package com.techelevator.dao;


import com.techelevator.model.TrainingResource.Category;
import java.util.List;
import java.util.Locale;

public interface CategoryDao {
    // Create
    public Category createCategory(Category category);

    // Read
    public List<Category> getAllCategories();
    public Category getCategoryByCategoryId(int categoryId);

    // Update
    public Category updateCategory(Category category);

    // Delete
    public void deleteCategory(int categoryId);
}
