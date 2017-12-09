import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class JavaOCR_Test {

    @Test
    public void OCR_Test() throws Exception {

        File file = new File("/home/nmodry/Pictures/moevenpick.jpg");
        BufferedImage img = ImageIO.read(file);
        ITesseract instance = new Tesseract();
        //instance.setDatapath("/home/nmodry/tmp/efood-hck-backend/tessdata");
        instance.setLanguage("eng+deu");

        System.err.println(instance.doOCR(img));

    }

}
