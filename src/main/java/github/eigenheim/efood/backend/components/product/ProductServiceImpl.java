package github.eigenheim.efood.backend.components.product;

import github.eigenheim.efood.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product get(String productId) {
        Product product = productRepository
                .findOne(productId);

        if (product == null) {
            throw new ResourceNotFoundException(
                    Product.class, productId);
        }

        return product;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
