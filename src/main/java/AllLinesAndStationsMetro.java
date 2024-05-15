import java.util.ArrayList;
import java.util.List;

public class AllLinesAndStationsMetro {
    private ArrayList<LineAndHerNamesMetro> stations = new ArrayList<>();


    public void setStations(LineAndHerNamesMetro station) {
        stations.add(station);
    }

    public ArrayList<LineAndHerNamesMetro> getStations() {
        return stations;
    }
}
