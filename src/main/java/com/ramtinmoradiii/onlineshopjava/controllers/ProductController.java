package com.ramtinmoradiii.onlineshopjava.controllers;

import com.ramtinmoradiii.onlineshopjava.common.ApiResponse;
import com.ramtinmoradiii.onlineshopjava.entities.Product;
import com.ramtinmoradiii.onlineshopjava.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
