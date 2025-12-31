package com.ramtinmoradiii.learnSpringJava.services.product;

import com.ramtinmoradiii.learnSpringJava.dto.product.ProductDTO;
import com.ramtinmoradiii.learnSpringJava.entities.product.Product;
import com.ramtinmoradiii.learnSpringJava.exceptions.NotFoundException;
import com.ramtinmoradiii.learnSpringJava.repositories.product.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public ProductService(ProductRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProductDTO add(ProductDTO dto) throws Exception {
        modelValidation(dto);
        Product product = mapper.map(dto, Product.class);
        product.setId(null);
        return mapper.map(repository.save(product), ProductDTO.class);
    }

    public ProductDTO update(ProductDTO dto) throws Exception {
        modelValidation(dto);
        if (dto.getId() == null || dto.getId() <= 0)
            throw new Exception("آیدی محصول نامعتبر است.");

        return mapper.map(repository.save(mapper.map(dto, Product.class)), ProductDTO.class);
    }

    public List<ProductDTO> getAll() {
        return repository.findAll().stream()
                .map(entity -> mapper.map(entity, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO getById(Long id) {
        return repository.findById(id)
                .map(entity -> mapper.map(entity, ProductDTO.class))
                .orElseThrow(() -> new NotFoundException("محصول مورد نظر یافت نشد."));
    }

    public ProductDTO getBySku(String sku) {
        return repository.findBySkuEqualsIgnoreCase(sku)
                .map(entity -> mapper.map(entity, ProductDTO.class))
                .orElseThrow(() -> new NotFoundException("SKU یافت نشد."));
    }

    public List<ProductDTO> getByBrand(String brand) {
        return repository.findByBrand(brand).stream()
                .map(entity -> mapper.map(entity, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new NotFoundException("محصول مورد نظر یافت نشد.");
        repository.deleteById(id);
    }

    private static void modelValidation(ProductDTO product) throws Exception {
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
