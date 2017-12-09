package github.eigenheim.efood.backend.components.textIndex;

import github.eigenheim.efood.backend.components.index.IndexService;
import github.eigenheim.efood.backend.components.product.Product;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

/**
 * Matches an image to a product.
 *
 * @author nmodry
 * @version 1.0
 */
public class TextMatcher implements IndexService {

    /**
     * A list of products that the read-in text should be matched to.
     */
    private List<String> itemWords;

    /**
     * Matches an image to a product.
     *
     * @param image the image that should be processed
     * @return the product_id of the most likely product
     * @throws RuntimeException if {@see github.eigenheim.efood.backend.components.textIndex#index} has
     *                          not been called before.
     */
    @Override
    public Map<Long, Double> match(BufferedImage image) {
        //FIXME implement
        return null;
    }


    /**
     * Initialize a list of words taken from product titles, manufactorers and descriptions to compare the text extracts with.
     *
     * @param products the list of products
     */
    @Override
    public void index(final List<Product> products) {

        if (products == null || products.size() == 0) {
            throw new NullPointerException("param must not be null or empty");
        }

        synchronized (this) {

            products.forEach(p -> {

                itemWords.addAll(TextPreprocessor.preprocess(p.getName()));
                itemWords.addAll(TextPreprocessor.preprocess(p.getDescription()));
                itemWords.addAll(TextPreprocessor.preprocess(p.getManufacturer()));

            });

        }

    }
}
