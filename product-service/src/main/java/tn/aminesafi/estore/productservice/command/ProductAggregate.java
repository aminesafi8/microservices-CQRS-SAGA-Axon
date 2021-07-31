package tn.aminesafi.estore.productservice.command;

import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import tn.aminesafi.estore.productservice.command.core.events.ProductCreatedEvent;

import java.math.BigDecimal;

@Aggregate
@NoArgsConstructor
// to let axon create everytime a new object and replay all the stored events toi build the object from scratch
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String title;
    private BigDecimal price;
    private int quantity;


    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        if (isValidCreateProductCommand(createProductCommand)) {
            // create and empty object
            ProductCreatedEvent productCreatedEvent = ProductCreatedEvent.builder().build();

            BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

            // dispatch the event to all the event handlers in this aggregate
            AggregateLifecycle.apply(productCreatedEvent);

        }
    }

    @EventSourcingHandler // will be scheduled for publication to other handlers and then stored in the event store
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.productId = productCreatedEvent.getProductId();
        this.title = productCreatedEvent.getTitle();
        this.price = productCreatedEvent.getPrice();
        this.quantity = productCreatedEvent.getQuantity();
    }


    private boolean isValidCreateProductCommand(CreateProductCommand createProductCommand) {
        if (createProductCommand.getPrice().compareTo((BigDecimal.ZERO)) <= 0) {
            throw new IllegalArgumentException("Price cannot be less or equal than ZERO");
        }
        if (StringUtils.isBlank(createProductCommand.getTitle())) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        return true;
    }
}
