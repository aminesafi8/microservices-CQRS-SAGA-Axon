package tn.aminesafi.estore.productservice.query.rest;

import lombok.AllArgsConstructor;
import lombok.val;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.aminesafi.estore.productservice.query.FindProductsQuery;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductQueryController {

    private final QueryGateway queryGateway;

    @GetMapping
    public List<ProductRestModel> getProducts() {
        FindProductsQuery findProductsQuery = new FindProductsQuery();

        val products = queryGateway.query(findProductsQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();

        return products;
    }
}
