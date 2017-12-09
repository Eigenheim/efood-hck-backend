package github.eigenheim.efood.backend.components.index;

import github.eigenheim.efood.backend.components.index.image.ImageIndexService;
import github.eigenheim.efood.backend.components.index.text.TextIndexService;
import github.eigenheim.efood.backend.components.product.Product;
import github.eigenheim.efood.backend.components.product.ProductService;
import github.eigenheim.efood.real.JsonReader;
import github.eigenheim.efood.real.ProductDTO;
import github.eigenheim.efood.real.api.RealProductApi;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class Indexer {
    private static final String[] products = {
            "812162_2_1", "751068_1_2",
            "628746_5_1", "326430_1_1"
    };

    @Autowired
    ProductService productService;

    @Autowired
    ImageIndexService imageIndexService;

    @Autowired
    TextIndexService textIndexService;

    public void run() throws Exception {
        List<Product> toIndex = new LinkedList<>();

        for (int i = 0; i < products.length; i++) {
            String productCode = products[i];
            Product product = fromDTO(RealProductApi
                    .getProduct(productCode));

            Product saved = productService.save(product);
            toIndex.add(saved);
        }

        imageIndexService.index(toIndex);
        textIndexService.index(toIndex);

    }

    private Product fromDTO(ProductDTO dto) {
        String code = dto.code;
        String name = dto.name;
        String manufacturer = dto.manufacturer;
        String description = dto.description;
        String imageUrl = dto.imageUrl;

        return new Product(code, name, manufacturer,
                description, imageUrl);
    }
}
