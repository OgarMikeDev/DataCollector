import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParseWebPage {
    private List<String> jsonFilePaths = new ArrayList<>();
    private List<String> csvFilePaths = new ArrayList<>();
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
        for (File currentFile : file.listFiles()) {
            if (currentFile.isDirectory()) {
                getFilesJsonAndCSV(currentFile);
            } else if (currentFile.getName().endsWith(".json")) {
                jsonFilePaths.add(currentFile.getPath());
                System.out.println(jsonFilePaths);
            } else if (currentFile.getName().endsWith(".csv")) {
                csvFilePaths.add(currentFile.getPath());
                System.out.println(csvFilePaths);
            }
        }
    }

    public void jsonToJava(File file) throws IOException {
        String json = Files.readString(file.toPath());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<FromJsonToJava> fromJsonToJavaObjects = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, FromJsonToJava.class));
        fromJsonToJavaObjects.forEach(System.out::println);

    }

    public void csvToJava(File file) throws IOException {

        List<String> lines = Files.readAllLines(file.toPath());

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",");

            String nameStation = parts[0];
            String numberLine = parts[1];

            FromCsvToJava stopCsv = new FromCsvToJava(nameStation, numberLine);

            System.out.println(stopCsv);

        }
    }

    public List<String> getJsonFilePaths() {
        return jsonFilePaths;
    }

    public List<String> getCsvFilePaths() {
        return csvFilePaths;
    }
}
