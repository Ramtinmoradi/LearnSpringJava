package com.ramtinmoradiii.onlineshopjava.services;

import com.ramtinmoradiii.onlineshopjava.entities.Product;
import com.ramtinmoradiii.onlineshopjava.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public Product add(Product product) throws Exception {
        modelValidation(product);
        return repository.create(product);
    }

    public Product update(Product product) throws Exception {
        modelValidation(product);
        if (product.getId() == null || product.getId() <= 0) throw new Exception("Please enter id.");
        return repository.update(product);
    }

    private static void modelValidation(Product product) throws Exception {
        if (product == null) throw new Exception("Product is NULL.");
        if (product.getBrand() == null || product.getBrand().isEmpty()) throw new Exception("Please enter brand.");
        if (product.getModel() == null || product.getModel().isEmpty()) throw new Exception("Please enter model.");
        if (product.getSku() == null || product.getSku().isEmpty()) throw new Exception("Please enter SKU.");
        if (product.getPrice() == null || product.getPrice() < 0) throw new Exception("Please enter price.");
    }
}
