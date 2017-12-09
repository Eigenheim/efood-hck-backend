package github.eigenheim.efood.backend;

import github.eigenheim.efood.backend.components.imagesearch.ImageSearchService;
import github.eigenheim.efood.backend.components.index.Indexer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        Indexer indexer = context.getBean(Indexer.class);
        indexer.run();

        ImageSearchService imageSearchService =
                context.getBean(ImageSearchService.class);

        BufferedImage queryImage = ImageIO
                .read(new File("src/main/resources/test/IMG_20171209_142033.jpg"));
        imageSearchService.search(queryImage);
    }
}
