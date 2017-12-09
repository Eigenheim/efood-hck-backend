package github.eigenheim.efood.backend.components.index.image;

import github.eigenheim.efood.backend.components.index.IndexService;
import github.eigenheim.efood.backend.components.index.ScoredResult;
import github.eigenheim.efood.backend.components.product.Product;
import net.semanticmetadata.lire.builders.DocumentBuilder;
import net.semanticmetadata.lire.builders.GlobalDocumentBuilder;
import net.semanticmetadata.lire.builders.LocalDocumentBuilder;
import net.semanticmetadata.lire.imageanalysis.features.global.CEDD;
import net.semanticmetadata.lire.searchers.GenericFastImageSearcher;
import net.semanticmetadata.lire.searchers.ImageSearchHits;
import net.semanticmetadata.lire.searchers.ImageSearcher;
import net.semanticmetadata.lire.utils.LuceneUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

@Service
public class ImageIndexService implements IndexService {
    private static final String INDEX_PATH = "src/main/resources/index";
    private static final int MAX_NUMBER_HITS = 20;

    private DocumentBuilder documentBuilder;
    private ImageSearcher imageSearcher;


    @PostConstruct
    public void init() throws IOException {
        documentBuilder = new GlobalDocumentBuilder(CEDD.class);
        imageSearcher = new GenericFastImageSearcher(MAX_NUMBER_HITS, CEDD.class);
    }

    @Override
    public List<ScoredResult> match(BufferedImage image) {
        List<ScoredResult> results = new LinkedList<>();

        try {
            IndexReader indexReader = DirectoryReader
                    .open(FSDirectory.open(Paths.get(INDEX_PATH)));
            ImageSearchHits hits = imageSearcher.search(image, indexReader);

            for (int i = 0; i < hits.length(); i++) {
                Document document = indexReader.document(hits.documentID(i));
                String productId = document.getValues(DocumentBuilder.FIELD_NAME_IDENTIFIER)[0];

                results.add(new ScoredResult(productId, hits.score(i)));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return results;
    }

    @Override
    public void index(List<Product> products) {
        try {
            IndexWriter indexWriter = LuceneUtils.createIndexWriter(INDEX_PATH, true,
                    LuceneUtils.AnalyzerType.WhitespaceAnalyzer);

            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);

                BufferedImage img = ImageIO.read(new URL(product.getImageUrl()));
                Document document = documentBuilder.createDocument(img, product.getId());

                indexWriter.addDocument(document);
            }

            LuceneUtils.closeWriter(indexWriter);

        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
