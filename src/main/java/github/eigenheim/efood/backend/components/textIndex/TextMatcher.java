package github.eigenheim.efood.backend.components.textIndex;

import github.eigenheim.efood.backend.components.product.Product;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Matches an image to a product.
 *
 * @author nmodry
 * @version 1.0
 */
public class TextMatcher implements TextIndexService{

    /**
     * Matches an image to a product.
     *
     * @param image the image that should be processed
     * @throws RuntimeException if {@see github.eigenheim.efood.backend.components.textIndex#index} has
     *                          not been called before.
     * @return the product_id of the most likely product
     */
    @Override
    public long match(BufferedImage image) {
        //FIXME implement
        return 0;
    }


    /**
     * Initialize a list of products to compare the text extracts with.
     *
     * @param products the list of products
     */
    @Override
    public void index(List<Product> products) {
        //FIXME implement
    }
}
