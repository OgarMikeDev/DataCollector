import java.util.ArrayList;

public class ListFullInformationMetro {
    private ArrayList<FullInformationMetro> stations = new ArrayList<>();

    public void setStations(FullInformationMetro station) {
        stations.add(station);
    }

    public ArrayList<FullInformationMetro> getStations() {
        return stations;
    }


    @Override
    public String toString() {
        return "All stations: " + getStations();
    }
}
