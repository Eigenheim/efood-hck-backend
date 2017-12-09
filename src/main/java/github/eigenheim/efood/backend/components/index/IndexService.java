package github.eigenheim.efood.backend.components.index;

import github.eigenheim.efood.backend.components.product.Product;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public interface IndexService {

    /**
     * Matches a given
     * query image with the index
     *
     * @param image Query image
     * @return (ProductId, Confidence)
     */
    List<ScoredResult> match(BufferedImage image);

    /**
     * Indexes the given
     * products
     *
     * @param products Products to
     *                 index
     */
    void index(List<Product> products);
}
