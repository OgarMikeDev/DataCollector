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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ParseWebPage {
    private Document document;
    private Elements elements;
    private List<FromJsonToJava> jsonObjects = new ArrayList<>();
    private ListFullInformationMetro listFullInformationMetro = new ListFullInformationMetro();

    //Method get html-code web-page
    public String getHtmlCodeWebPage() throws IOException {
        String url = "https://skillbox-java.github.io/";
        document = Jsoup.connect(url).get();
        String htmlCode = String.valueOf(document);
        FileWriter fileWriter = new FileWriter("src/main/resources/codeHtmlMetro.html");
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
            FileWriter fileWriter = new FileWriter("src/main/resources/stations.json");
            fileWriter.write(jsonStation);
            fileWriter.close();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void getFilesJsonAndCSV(File file) throws IOException {
        for (File currentFile : file.listFiles()) {
            if (currentFile.isDirectory()) {
                getFilesJsonAndCSV(currentFile);
            } else if (currentFile.getName().endsWith(".json")) {
                String json = Files.readString(currentFile.toPath());
                ObjectMapper objectMapper = new ObjectMapper();
                jsonObjects = objectMapper.readValue(json,
                        objectMapper.getTypeFactory().constructCollectionType(List.class,
                                FromJsonToJava.class));
                //jsonObjects.forEach(System.out::println);

            } else if (currentFile.getName().endsWith(".csv")) {
                List<String> lines = Files.readAllLines(currentFile.toPath());
                //System.out.println("List lines \"" + lines + "\".");
                for (int i = 1; i < lines.size(); i++) {
                    String line = lines.get(i);
                    String[] parts = line.split(",");

                    String nameStation = parts[0];
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.
                            ofPattern("dd.MM.yyyy");
                    LocalDate numberLine = LocalDate.parse(parts[1], dateTimeFormatter);

                    FromCsvToJava fromCsvToJava = new FromCsvToJava(nameStation, numberLine);
                    //System.out.println(fromCsvToJava);
                    for (FromJsonToJava currentObjectJson : jsonObjects) {
                        if (currentObjectJson.getStation_name().equals(nameStation)) {
                            FullInformationMetro fullInformationMetro = new FullInformationMetro(
                                    currentObjectJson.getStation_name(),
                                    currentObjectJson.getDepth(),
                                    numberLine
                            );
                            listFullInformationMetro.setStations(fullInformationMetro);
                            System.out.println(fullInformationMetro);
                            ObjectMapper objectMapper = new ObjectMapper();
//                            String strFullInfMetro = objectMapper.writeValueAsString(fullInformationMetro);
//                            FileWriter fileWriter = new FileWriter("data/listFullInformationMetro.json");
//                            fileWriter.write(strFullInfMetro);
//                            fileWriter.flush();
//                            fileWriter.close();
                        }
                    }
                }
            }
        }
    }
}
