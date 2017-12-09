package github.eigenheim.efood.real.api;

import github.eigenheim.efood.real.JsonReader;
import github.eigenheim.efood.real.ProductDTO;
import github.eigenheim.efood.real.SearchResultDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.util.HtmlUtils;

import java.util.LinkedList;
import java.util.List;

public class RealProductApi {
    private static final String REAL_IMG_ENDPOINT = "https://www.real.de/lebensmittelshop";
    private static final String REAL_API_ENDPOINT = "https://api.efood.real.de/api/v2/real/products/";
    private static final String REAL_API_ENDPOINT_SEARCH = REAL_API_ENDPOINT + "search?query=";

    public static List<SearchResultDTO> search(String query) throws Exception {
        String searchQuery = REAL_API_ENDPOINT_SEARCH + HtmlUtils.htmlEscape(query)
                .replace(" ", "+");
        List<SearchResultDTO> results = new LinkedList<>();

        JSONObject result = JsonReader.readJsonFromUrl(searchQuery);
        JSONArray products = (JSONArray) result.get("products");

        for (int i = 0; i < products.length(); i++) {
            JSONObject productJson = products.getJSONObject(i);

            String name = (String) productJson.get("name");
            String code = (String) productJson.get("code");
            SearchResultDTO searchResult = new SearchResultDTO(name, code);

            results.add(searchResult);
        }

        return results;
    }

    public static ProductDTO getProduct(String code) throws Exception {
        String productDetail = REAL_API_ENDPOINT + code;
        JSONObject result = JsonReader.readJsonFromUrl(productDetail);

        return parseProduct(result);
    }

    private static ProductDTO parseProduct(JSONObject json) {
        ProductDTO product = new ProductDTO();

        product.name = (String) json.get("name");
        product.code = (String) json.get("code");
        product.description = (String) json.get("description");
        product.manufacturer = (String) json.get("brand");
        product.imageUrl = getImagePath(json);

        return product;
    }

    private static String getImagePath(JSONObject product) {
        JSONArray images = (JSONArray) product.get("images");
        JSONObject image = (JSONObject) images.get(1);

        return REAL_IMG_ENDPOINT + image.get("url");
    }

    public static ProductDTO getProductByName(String name) throws Exception {
        SearchResultDTO topResult = search(name).get(0);

        return getProduct(topResult.getCode());
    }

}
