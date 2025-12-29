package com.ramtinmoradiii.onlineshopjava.dto;

import com.ramtinmoradiii.onlineshopjava.entities.Color;
import com.ramtinmoradiii.onlineshopjava.entities.Product;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class MockData {
    @Getter
    private static final List<Product> products;
    private static final List<Color> colors;

    static {
        products = new ArrayList<>();
        colors = new ArrayList<>();
        colors.add(Color.builder().id(1L).name("Ultramarine").hexValue("#0071E3").build());
        colors.add(Color.builder().id(2L).name("Pink").hexValue("#F3B5DE").build());
        products.add(Product.builder().id(1L).brand("Apple").model("13ProMax").price(1200L).sku("AI13PM").colors(colors).build());
    }

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static void removeProduct(Long id) {
        products.stream().filter(product -> product.getId().equals(id)).findFirst().ifPresent(product -> products.remove(product));
    }

    public static List<Color> getColor() {
        return colors;
    }

    public static void addColor(Color color) {
        colors.add(color);
    }

    public static void removeColor(Long id) {
        colors.stream().filter(color -> color.getId().equals(id)).findFirst().ifPresent(color -> colors.remove(color));
    }
}
