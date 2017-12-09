package github.eigenheim.efood.backend.components.imagesearch;

import github.eigenheim.efood.backend.components.index.ScoredResult;
import github.eigenheim.efood.backend.components.index.image.ImageIndexService;
import github.eigenheim.efood.backend.components.index.text.TextIndexService;
import github.eigenheim.efood.backend.components.product.Product;
import github.eigenheim.efood.backend.components.product.ProductService;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;

@Service
public class ImageSearchServiceImpl implements ImageSearchService {


    @Autowired
    ImageIndexService imageIndexService;

    @Autowired
    TextIndexService textIndexService;

    @Autowired
    ProductService productService;

    @Override
    public Product search(BufferedImage image) {
        Product product = null;

        try {
            List<ScoredResult> searchResults = Collections.emptyList();
            List<ScoredResult> resultsLocalImageFeatures =
                    imageIndexService.match(image);
            List<ScoredResult> resultsTextFeatures =
                textIndexService.match(image);

            searchResults.addAll(resultsLocalImageFeatures);
            searchResults.addAll(resultsTextFeatures);

            Collections.sort(searchResults);

            if (searchResults.size() > 0) {
                ScoredResult topResult = searchResults.get(0);
                product = productService.get(topResult.getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }
}
