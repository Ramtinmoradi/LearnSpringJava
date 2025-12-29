package com.ramtinmoradiii.onlineshopjava.repositories;

import com.ramtinmoradiii.onlineshopjava.dto.MockData;
import com.ramtinmoradiii.onlineshopjava.entities.Product;
import com.ramtinmoradiii.onlineshopjava.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    public Product create(Product product) {
        Long id = MockData.getProducts().get(MockData.getProducts().size() - 1).getId() + 1;
        product.setId(id);
        MockData.addProduct(product);
        return product;
    }

    public List<Product> findAll() {
        return MockData.getProducts();
    }

    public Product findById(Long id) {
        return MockData.getProducts()
                .stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("محصول با ایدی " + id + " یافت نشد!"));
    }

    public Product update(Product product) {
        Product oldData = findById(product.getId());
        oldData.setBrand(product.getBrand());
        oldData.setModel(product.getModel());
        oldData.setColors(product.getColors());
        oldData.setSku(product.getSku());
        oldData.setPrice(product.getPrice());
        return oldData;
    }

    public void delete(Long id) {
        findById(id);
        MockData.removeProduct(id);
    }
}
