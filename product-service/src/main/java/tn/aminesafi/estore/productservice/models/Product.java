package tn.aminesafi.estore.productservice.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private String title;
    private BigDecimal price;
    private int quantity;
}
