package com.ramtinmoradiii.learnSpringJava.controllers;

import com.ramtinmoradiii.learnSpringJava.common.ApiResponse;
import com.ramtinmoradiii.learnSpringJava.entities.Product;
import com.ramtinmoradiii.learnSpringJava.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Product>> getAll() {
        return ApiResponse.success(service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Product> getById(@PathVariable Long id) {
        return ApiResponse.success(service.getById(id));
    }

    @PostMapping()
    public ApiResponse<Product> add(@RequestBody Product product) throws Exception {
        return ApiResponse.success(service.add(product));
    }

    @PutMapping()
    public ApiResponse<Product> update(@RequestBody Product product) throws Exception {
        return ApiResponse.success(service.update(product));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteById(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.success();
    }
}
