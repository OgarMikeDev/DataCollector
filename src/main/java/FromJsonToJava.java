public class FromJsonToJava {
    private String station_name;
    private String depth;

    public String getStation_name() {
        return station_name;
    }

    public String getDepth() {
        return depth;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "From json to java:\n" +
                "\tStation name \"" + station_name + "\"," +
                "\n\tDepth \"" + depth + "\".\n";
    }
}
