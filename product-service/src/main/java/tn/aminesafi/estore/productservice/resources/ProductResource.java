package tn.aminesafi.estore.productservice.resources;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductResource {

    private final Environment environment;

    @GetMapping
    public String getProduct() {
        return "HTTP GET Handled "+ environment.getProperty("local.server.port");
    }


    @PostMapping
    public String createProduct() {
        return "HTTP POST Handled";
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
