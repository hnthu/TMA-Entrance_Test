package controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.CategoryService;

@RestController
@PreAuthorize("hasAnyRole(\"ROLE_USER\",\"ROLE_ADMIN\")")
public class CategoryController {
    protected final Logger logger2 = LogManager.getLogger();
    private CategoryService categoryService;

    @Autowired
    public void setCatgoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/getallcategories", method = RequestMethod.GET)
    public Object getAllCategories() {
        logger2.info("is running get all Category in the database.");
        logger2.debug("is running get all Category in the database.");
        logger2.error("is running get all Category in the database.");
        return categoryService.getAll();
    }

    @RequestMapping(value = "/getcategorybyid/{id}", method = RequestMethod.GET)
    public Object getCategoryById(@PathVariable("id") int id) {
        return categoryService.getCategoryById(id);
    }

    @RequestMapping(value ="/deletecategory/{id}", method = RequestMethod.DELETE)
    public String deleteCategory(@PathVariable("id") int id){
        Category existingCategory = this.categoryService.getCategoryById(id);
        if(existingCategory != null){
            this.categoryService.delete(id);
        }
        return "Deleted Successfully";
    }

    @RequestMapping(value = "/updatecategory/{id}", method = RequestMethod.PUT)
    public Object updateCategory(@PathVariable("id") int id,  @RequestBody Category updateCategory){
        Category currentCategory = this.categoryService.getCategoryById(id);
        if(currentCategory != null){
            this.categoryService.update(updateCategory);
        }
        return "Updated successfully";
    }

    @RequestMapping(value = "/addcategory", method = RequestMethod.POST)
    public String addCategory(@RequestBody Category addCategory){
        this.categoryService.add(addCategory);
        return "Added successfully";
    }
}
