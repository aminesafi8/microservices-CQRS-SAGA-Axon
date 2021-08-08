package tn.aminesafi.estore.productservice.query;

import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tn.aminesafi.estore.productservice.core.data.ProductEntity;
import tn.aminesafi.estore.productservice.core.data.ProductRepository;
import tn.aminesafi.estore.productservice.core.events.ProductCreatedEvent;

@Component
@AllArgsConstructor
public class ProductEventsHandler { // Projection class we can name it also ProductProjection

    private final ProductRepository productRepository;

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);
        productRepository.save(productEntity);
    }
}
