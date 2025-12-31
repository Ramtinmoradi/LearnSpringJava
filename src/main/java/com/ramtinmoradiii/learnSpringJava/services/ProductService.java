package com.ramtinmoradiii.learnSpringJava.services;

import com.ramtinmoradiii.learnSpringJava.entities.Product;
import com.ramtinmoradiii.learnSpringJava.exceptions.ResourceNotFoundException;
import com.ramtinmoradiii.learnSpringJava.repositories.ProductRepository;
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

    public Product add(Product product) throws Exception {
        modelValidation(product);
        product.setId(null);
        return repository.save(product);
    }

    public Product update(Product product) throws Exception {
        modelValidation(product);
        if (product.getId() == null || product.getId() <= 0) throw new Exception("لطفا آیدی محصول را وارد نمایید.");
        return repository.save(product);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("محصول مورد نظر یافت نشد."));
    }

    public Product getBySku(String sku) {
        return repository.findBySkuEqualsIgnoreCase(sku).orElseThrow(() -> new ResourceNotFoundException("محصول مورد نظر یافت نشد."));
    }

    public List<Product> getByBrand(String brand) {
        return repository.findByBrand(brand);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private static void modelValidation(Product product) throws Exception {
        if (product == null) throw new Exception("محصول یافت نشد.");
        if (product.getBrand() == null || product.getBrand().isEmpty())
            throw new Exception("لطفا برند محصول را وارد کنید.");
        if (product.getModel() == null || product.getModel().isEmpty())
            throw new Exception("لطفا مدل محصول را وارد کنید.");
        if (product.getSku() == null || product.getSku().isEmpty())
            throw new Exception("لطفا شناسه دستگاه را وارد کنید.");
        if (product.getPrice() == null || product.getPrice() < 0) throw new Exception("لطفا قیمت محصول را وارد کنید.");
    }
}
