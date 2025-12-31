package com.ramtinmoradiii.learnSpringJava.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({"id", "brand", "model", "sku", "price", "colors"})
public class ProductDTO {
    private Long id;
    private String brand;
    private String model;
    private String sku;
    private Long price;
    private List<ColorDTO> colors;
}
