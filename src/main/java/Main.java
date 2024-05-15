import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ParseWebPage parseWebPage = new ParseWebPage();
        parseWebPage.getHtmlCodeWebPage();
        parseWebPage.getNamesAndLineStation();
        parseWebPage.fromJsonAsJavaStations();
    }
}
