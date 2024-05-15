import java.util.ArrayList;
import java.util.List;

public class AllStationsMetro {
    private List<StationMetro> stations = new ArrayList<>();


    public void setStations(StationMetro station) {
        stations.add(station);
    }

    public List<StationMetro> getStations() {
        return stations;
    }
}
