package pl.edu.agh.niebieskiekotki.utility;

import org.junit.jupiter.api.Test;
import pl.edu.agh.niebieskiekotki.DataBaseMock;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import static pl.edu.agh.niebieskiekotki.utility.FileCreator.createFileWithPreferences;

public class FileCreatorTests {
    public static void main(String[] args) {
        try {
            createFileWithPreferences(DataBaseMock.questionnaires.get(0), Language.POLISH);
        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
