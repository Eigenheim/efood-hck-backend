package github.eigenheim.efood.backend.components.imagesearch;

import github.eigenheim.efood.backend.components.product.Product;

import java.awt.image.BufferedImage;

public interface ImageSearchService {

    /**
     *
     *
     * @param image Query image
     * @return Recognized product
     */
    Product search(BufferedImage image);

}
