import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ParseWebPage parseWebPage = new ParseWebPage();
        parseWebPage.getHtmlCodeWebPage();
        parseWebPage.getNamesAndLineStation();
        parseWebPage.getFilesJsonAndCSV(new File("data/"));
        for (String jsonFilePath : parseWebPage.getJsonFilePaths()) {
            parseWebPage.jsonToJava(new File(jsonFilePath));
        }

        for (String csvFilePath : parseWebPage.getCsvFilePaths()) {
            parseWebPage.csvToJava(new File(csvFilePath));
        }
    }
}
