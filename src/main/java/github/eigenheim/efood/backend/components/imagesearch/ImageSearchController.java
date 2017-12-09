package github.eigenheim.efood.backend.components.imagesearch;

import github.eigenheim.efood.backend.components.product.Product;
import github.eigenheim.efood.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/search")
public class ImageSearchController {

    @Autowired
    ImageSearchService imageSearchService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    public Product search(@RequestParam("image") MultipartFile image) {
        Product product = null;

        try {
            InputStream in = new ByteArrayInputStream(image.getBytes());
            BufferedImage queryImage = ImageIO.read(in);

            product = imageSearchService.search(queryImage);

            if (product == null) {
                throw new ResourceNotFoundException(Product.class, "");
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return product;
    }
}
