package tn.aminesafi.estore.productservice.query;

import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tn.aminesafi.estore.productservice.core.data.ProductEntity;
import tn.aminesafi.estore.productservice.core.data.ProductRepository;
import tn.aminesafi.estore.productservice.query.rest.ProductRestModel;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductsQueryHandler {

    private final ProductRepository productRepository;

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductsQuery query) {

        return productRepository.findAll()
                .stream()
                .map(this::toProductRestModel)
                .collect(Collectors.toList());
    }

    private ProductRestModel toProductRestModel(ProductEntity productEntity) {
        ProductRestModel productRestModel = new ProductRestModel();
        BeanUtils.copyProperties(productEntity, productRestModel);
        return productRestModel;
    }

}
