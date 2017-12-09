package github.eigenheim.efood.backend.components.index;

import github.eigenheim.efood.backend.components.index.image.ImageIndexService;
import github.eigenheim.efood.backend.components.product.Product;
import github.eigenheim.efood.backend.components.product.ProductService;
import github.eigenheim.efood.backend.util.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class Indexer {
    private static final String DATA_PATH = "src/main/resources/data.json";

    @Autowired
    ProductService productService;

    @Autowired
    ImageIndexService imageIndexService;

    public void run() throws Exception {
        List<Product> productList = new LinkedList<>();
        JSONObject data = JsonReader.readJsonFromFile(DATA_PATH);
        JSONArray products = (JSONArray) data.get("data");

        for (int i = 0; i < products.length(); i++) {
            Product product = fromJson((JSONObject) products.get(i));

            Product saved = productService.save(product);
            productList.add(saved);
        }

        imageIndexService.index(productList);
    }

    private Product fromJson(JSONObject json) {
        String name = json.getString("name");
        String manufacturer = json.getString("manufacturer");
        String description = json.getString("description");
        String imageUrl = json.getString("imageUrl");

        return new Product(name, manufacturer,
                description, imageUrl);
    }
}
