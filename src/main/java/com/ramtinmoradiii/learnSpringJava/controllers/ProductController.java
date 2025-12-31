package com.ramtinmoradiii.learnSpringJava.controllers;

import com.ramtinmoradiii.learnSpringJava.common.ApiResponse;
import com.ramtinmoradiii.learnSpringJava.dto.ProductDTO;
import com.ramtinmoradiii.learnSpringJava.services.ProductService;
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
    public ApiResponse<List<ProductDTO>> getAll() {
        return ApiResponse.success(service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductDTO> getById(@PathVariable Long id) {
        return ApiResponse.success(service.getById(id));
    }

    @GetMapping("/sku/{sku}")
    public ApiResponse<ProductDTO> getBySku(@PathVariable String sku) {
        return ApiResponse.success(service.getBySku(sku));
    }

    @GetMapping("/brand/{brand}")
    public ApiResponse<List<ProductDTO>> getByBrand(@PathVariable String brand) {
        return ApiResponse.success(service.getByBrand(brand));
    }

    @PostMapping()
    public ApiResponse<ProductDTO> add(@RequestBody ProductDTO product) throws Exception {
        return ApiResponse.success(service.add(product));
    }

    @PutMapping()
    public ApiResponse<ProductDTO> update(@RequestBody ProductDTO product) throws Exception {
        return ApiResponse.success(service.update(product));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteById(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.success();
    }
}
