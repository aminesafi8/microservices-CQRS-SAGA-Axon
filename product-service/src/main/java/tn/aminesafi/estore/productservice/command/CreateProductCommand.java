package tn.aminesafi.estore.productservice.command;

import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Builder
@Getter
public class CreateProductCommand {

    @TargetAggregateIdentifier
    private final String productId;

    private final String title;
    private final BigDecimal price;
    private final int quantity;
}
