import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

        ObjectMapper objectMapper = new ObjectMapper();
        StationMetro stationMetro = new StationMetro();

        String regexForNumber = "data-line=\"([0-9]{1,2})|([0-9]+[A-Z]+)|([A-Z]+[0-9]+)\"";
        elements = document.select(".js-metro-stations");
        elements.forEach(elem -> {
            String nameStation = elem.select(".name").text();
            String numberLine = elem.attr("data-line");

            stationMetro.setName(nameStation);
            stationMetro.setNumberLineMetro(numberLine);
            System.out.println("Number line \"" + numberLine + "\"\nName station \"" + nameStation + "\"");

        });
        try {
            String jsonStation = objectMapper.writeValueAsString(stationMetro);
            FileWriter fileWriter = new FileWriter("data/stations.json");
            fileWriter.write(jsonStation);
            fileWriter.close();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
