package controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.CategoryService;

@RestController
@RequestMapping(value = "/v1")
@PreAuthorize("hasAnyRole(\"ROLE_USER\",\"ROLE_ADMIN\")")
public class CategoryController {
    protected final Logger logger2 = LogManager.getLogger();
    private CategoryService categoryService;

    @Autowired
    public void setCatgoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public Object getAllCategories() {
        return categoryService.getAll();
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public Object getCategoryById(@PathVariable("id") int id) {
        return categoryService.getCategoryById(id);
    }

    @RequestMapping(value ="/categories/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable("id") int id){
        Category existingCategory = this.categoryService.getCategoryById(id);
        if(existingCategory != null){
            this.categoryService.delete(id);
        }
        return new ResponseEntity("Deleted Successfully", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.PUT)
    public  ResponseEntity<?> updateCategory(@RequestBody Category updateCategory){
        Category currentCategory = this.categoryService.getCategoryById(updateCategory.getCategoryId());
        if(currentCategory != null){
            this.categoryService.update(updateCategory);
        }
        return new ResponseEntity("Updated Successfully", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public  ResponseEntity<?> addCategory(@RequestBody Category addCategory){
        this.categoryService.add(addCategory);
        return new ResponseEntity("Added Successfully", new HttpHeaders(), HttpStatus.OK);
    }
}
