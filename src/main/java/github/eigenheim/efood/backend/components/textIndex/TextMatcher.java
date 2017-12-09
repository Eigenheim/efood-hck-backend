package github.eigenheim.efood.backend.components.textIndex;

import github.eigenheim.efood.backend.components.index.IndexService;
import github.eigenheim.efood.backend.components.product.Product;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;
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
     * A map of products that the read-in text should be matched to.
     */
    private HashMap<Product, List<String>> products;

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

        final List<String> detectedWords = TextPreprocessor.preprocess(TextExtractor.extract(image));
        final HashMap<Product, Integer> equals = new HashMap<>();

        detectedWords.forEach(w -> {

            this.products.forEach((k,v)->{

                int equal = (int) v.stream().filter(iw -> iw.equals(w)).count();

                equals.put(k, equals.get(k) + equal);

            });

        });

        final HashMap<Long, Double> matches = new HashMap<>();
        equals.forEach((k,v)-> {
            matches.put(k.getId(), ((double) v)/((double) this.products.get(k).size()));
        });

        return matches;
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

                LinkedList<String> itemWords = new LinkedList<>();

                itemWords.addAll(TextPreprocessor.preprocess(p.getName()));
                itemWords.addAll(TextPreprocessor.preprocess(p.getDescription()));
                itemWords.addAll(TextPreprocessor.preprocess(p.getManufacturer()));

                this.products.put(p, itemWords);
            });

        }

    }
}
