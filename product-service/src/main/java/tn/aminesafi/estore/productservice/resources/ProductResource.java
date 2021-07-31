package tn.aminesafi.estore.productservice.resources;

import lombok.AllArgsConstructor;
import lombok.val;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import tn.aminesafi.estore.productservice.command.CreateProductCommand;
import tn.aminesafi.estore.productservice.models.Product;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductResource {

    private final Environment environment;
    private final CommandGateway commandGateway;

    @GetMapping
    public String getProduct() {
        return "HTTP GET Handled " + environment.getProperty("local.server.port");
    }


    @PostMapping
    public String createProduct(@RequestBody Product product) {

        val createProductCommand = CreateProductCommand.builder()
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .title(product.getTitle())
                .productId(UUID.randomUUID().toString())
                .build();

        String returnValue;
        try {
            returnValue = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception ex) {
            returnValue = ex.getLocalizedMessage();
        }

        return returnValue;
    }


    @PutMapping
    public String updateProduct() {
        return "HTTP PUT Handled";
    }


    @DeleteMapping
    public String deleteProduct() {
        return "HTTP DELETE Handled";
    }
}
