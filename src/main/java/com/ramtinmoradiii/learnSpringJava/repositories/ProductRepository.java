package com.ramtinmoradiii.learnSpringJava.repositories;

import com.ramtinmoradiii.learnSpringJava.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySkuEqualsIgnoreCase(String sku);

    @Query(value = "SELECT p FROM Product p WHERE p.brand LIKE %:brandName%")
    List<Product> findByBrand(@Param("brandName") String brand);
}
