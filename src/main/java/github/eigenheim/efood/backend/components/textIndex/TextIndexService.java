package github.eigenheim.efood.backend.components.textIndex;

import github.eigenheim.efood.backend.components.product.Product;

import java.awt.image.BufferedImage;
import java.util.List;

public interface TextIndexService {

    long match(BufferedImage image);

    void index(List<Product> products);
}
