import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        AllLinesAndStationsMetro allLinesAndStationsMetro = new AllLinesAndStationsMetro();

        //String regexForNumber = "data-line=\"([0-9]{1,2})|([0-9]+[A-Z]+)|([A-Z]+[0-9]+)\"";
        elements = document.select(".js-metro-stations");
        elements.forEach(elem -> {
            String nameStation = elem.select(".name").text();
            String numberLine = elem.attr("data-line");

            LineAndHerNamesMetro lineAndHerNamesMetro = new LineAndHerNamesMetro();
            lineAndHerNamesMetro.setNames(nameStation);
            lineAndHerNamesMetro.setNumberLineMetro(numberLine);
            allLinesAndStationsMetro.setStations(lineAndHerNamesMetro);

            System.out.println("Number line \"" + numberLine + "\"\nName station \"" + nameStation + "\"");
        });

        try {
            String jsonStation = objectMapper.writeValueAsString(allLinesAndStationsMetro);
            FileWriter fileWriter = new FileWriter("data/stations.json");
            fileWriter.write(jsonStation);
            fileWriter.close();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }


    public void fromJsonAsJavaStations() throws IOException {
        String json = Files.readString(Paths.get("data/stations.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        AllLinesAndStationsMetro allLinesAndStationsMetro = objectMapper.readValue(json, AllLinesAndStationsMetro.class);
        System.out.println(allLinesAndStationsMetro.toString());
    }

    public void getFilesJsonAndCSV(File file) {
    }
}
