package github.eigenheim.efood.backend.components.textIndex;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Preprocesses a String before it can be matched.
 *
 * @author nmodry
 * @version 1.0
 */
public class TextPreprocessor {

    /**
     * Preprocesses a given String.
     *
     * Removes all non-alphabetic characters and splits into words.
     *
     * @param s the String that should be processed
     * @return a list of preprocessed words
     */
    public static List<String> preprocess (String s) {

        String[] lines = s.split("\n");
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].replaceAll("[^a-zA-Z]", "");
        }

        final LinkedList<String> words = new LinkedList<>();

        for (final String line : lines) {

            words.addAll(Arrays.asList(line.split(" ")));

        }

        return words;
    }

}
