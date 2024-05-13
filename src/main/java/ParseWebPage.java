import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseWebPage {
    private Document document;
    private Elements elements;

    //Method get html-code web-page
    public String getHtmlCodeWebPage() throws IOException {
        String url = "https://skillbox-java.github.io/";
        document = Jsoup.connect(url).get();
        String htmlCode = String.valueOf(document);
        FileWriter fileWriter = new FileWriter("data/codeHtmlMetro.html");
        fileWriter.write(htmlCode);
        return htmlCode;
    }

    //Method get names and number line station
    public void getNamesAndLineStation() {
//        elements = document.select(".name");
//        System.out.println("All stations:");
//        elements.forEach(elem -> System.out.println(elem.text()));
        String regexForNumber = "data-line=\"([0-9]{1,2})|([0-9]+[A-Z]+)|([A-Z]+[0-9]+)\"";
        elements = document.select(".js-metro-stations");
        elements.forEach(elem -> {
            Pattern pattern = Pattern.compile(regexForNumber);
            Matcher matcher = pattern.matcher(String.valueOf(elem));
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                String line = String.valueOf(elem).substring(start, end);
                if (line.contains("data-line")) {
                    System.out.println(line.replaceAll("[^0-9]{1,2}", ""));
                } else {
                    System.out.println(line.replaceAll("\"", ""));
                }
                System.out.println(elem.text());
            }
        });
    }
}
