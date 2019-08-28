package com.gl.eshopping.controller;

import com.gl.eshopping.dao.CategoryDAO;
import com.gl.eshopping.model.Category;
import com.gl.eshopping.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
public class CategoryController {


    private CategoryDAO categoryDAO;

    @Autowired
    public CategoryController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<Category>> getAllCategory() {
        log.debug("Getting All Category.");
        return ResponseEntity.status(HttpStatus.OK).body(categoryDAO.findAll());
    }

    @GetMapping(value = "/category/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable Long categoryId) {
        log.debug("Getting category By Id.");
        return ResponseEntity.status(HttpStatus.OK).body(categoryDAO.findById(categoryId));
    }

    @GetMapping(value = "/category/{categoryId}/products")
    public ResponseEntity<List<Product>> getCategoryProducts(@PathVariable Long categoryId) {
        log.debug("Getting category products.");
        return ResponseEntity.status(HttpStatus.OK).body(categoryDAO.getCategoryProducts(categoryId));
    }

    @GetMapping(value = "/category/{categoryId}/products/{productId}")
    public ResponseEntity<Product> getCategoryProductById(@PathVariable Long categoryId, @PathVariable Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryDAO.getCategoryProductById(categoryId, productId));
    }

    @PostMapping(value = "/admin/category")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        log.debug("Saving category.");
        categoryDAO.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDAO.save(category));
    }

    @PutMapping(value = "/admin/category/{categoryId}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        log.debug("Updating category.");
        return ResponseEntity.status(HttpStatus.OK).body(categoryDAO.save(category));
    }

    @DeleteMapping(value = "/admin/category")
    public ResponseEntity<?> deleteCategory(@RequestBody Category category) {
        log.debug("Deleting Category.");
        //category.setActive(false);
        categoryDAO.delete(category);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/admin/category/{categoryId}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long categoryId) {
        log.debug("Deleting Category By Id.");
        categoryDAO.deleteById(categoryId);
        return ResponseEntity.noContent().build();
    }



}
