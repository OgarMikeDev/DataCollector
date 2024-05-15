import java.util.ArrayList;
import java.util.List;

public class AllLinesAndStationsMetro {
    private List<LineAndHerNamesMetro> stations = new ArrayList<>();


    public void setStations(LineAndHerNamesMetro station) {
        stations.add(station);
    }

    public List<LineAndHerNamesMetro> getStations() {
        return stations;
    }
}
