package github.eigenheim.efood.backend.components.imagesearch;

import github.eigenheim.efood.backend.components.index.ScoredResult;
import github.eigenheim.efood.backend.components.index.image.ImageIndexService;
import github.eigenheim.efood.backend.components.product.Product;
import github.eigenheim.efood.backend.components.product.ProductService;
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
    ProductService productService;

    @Override
    public Product search(BufferedImage image) {
        Product product = null;

        try {
            List<ScoredResult> resultsLocalImageFeatures =
                    imageIndexService.match(image);

            Collections.sort(resultsLocalImageFeatures);
            ScoredResult topResult = resultsLocalImageFeatures.get(0);

            product = productService.get(topResult.getId());
        } catch (Exception e) {
            System.err.println(e);
        }

        return product;
    }
}
