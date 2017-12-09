package github.eigenheim.efood.backend.components.textIndex;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;

/**
 * Extracts text from an image
 *
 * @author nmodry
 * @version 1.0
 */
public class TextExtractor {

    public static String extract(BufferedImage image) {

        ITesseract instance = new Tesseract();
        instance.setLanguage("deu+eng");

        String result = null;

        try {
            result = instance.doOCR(image);
        } catch (TesseractException e) {
            result = null;
        }

        return result;
    }

}
