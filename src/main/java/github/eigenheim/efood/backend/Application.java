package github.eigenheim.efood.backend;

import github.eigenheim.efood.backend.components.index.Indexer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        Indexer indexer = context.getBean(Indexer.class);
        indexer.run();
    }
}
