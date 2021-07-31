package tn.aminesafi.estore.productservice.command.core.events;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductCreatedEvent {

    private String productId;

    private String title;
    private BigDecimal price;
    private int quantity;
}
