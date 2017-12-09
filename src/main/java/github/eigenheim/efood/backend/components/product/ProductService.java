package github.eigenheim.efood.backend.components.product;

public interface ProductService {
    /**
     * Finds and returns
     * a product
     *
     * @param productId ProductId
     * @return Product
     */
    Product get(String productId);

    /**
     * Saves a product to
     * the database
     *
     * @param product Product
     */
    Product save(Product product);
}
