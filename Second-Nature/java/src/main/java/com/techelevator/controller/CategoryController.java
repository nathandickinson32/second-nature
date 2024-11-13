package com.techelevator.controller;

import com.techelevator.dao.CategoryDao;
import com.techelevator.model.TrainingResource.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/category")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class CategoryController {
    @Autowired
    private CategoryDao categoryDao;

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(path = "/create")
    public Category createCategory(@RequestBody Category category, Principal principal){
        System.out.println(LocalDateTime.now() + " [Category Controller] User: " + principal.getName() + " is creating a new category.");
        return categoryDao.createCategory(category);
    }

    @GetMapping(path = "/all")
    public List<Category> getAllCategories(Principal principal){
        System.out.println(LocalDateTime.now() + "[Category Controller] User " + principal.getName() + " is getting a list of all categories.");
        return categoryDao.getAllCategories();
    }

    @GetMapping(path = "/{categoryId}")
    public Category getCategoryByCategoryId(@PathVariable int categoryId, Principal principal){
        System.out.println(LocalDateTime.now() + "[Category Controller] User " + principal.getName() + " is getting category by ID: " + categoryId);
        return categoryDao.getCategoryByCategoryId(categoryId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "/update")
    public Category updateType(@RequestBody Category category, Principal principal){
        System.out.println(LocalDateTime.now() + "[Category Controller] User " + principal.getName() + " is updating category by ID: " + category.getCategoryId());
        return categoryDao.updateCategory(category);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/delete/{categoryId}")
    public void deleteCategory(@PathVariable int categoryId, Principal principal){
        System.out.println(LocalDateTime.now() + "[Category Controller] User " + principal.getName() + " is deleting category by ID: " + categoryId);
        categoryDao.deleteCategory(categoryId);
    }

}
