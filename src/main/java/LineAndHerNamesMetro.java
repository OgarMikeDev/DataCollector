import java.util.ArrayList;

public class LineAndHerNamesMetro {
    private String numberLineMetro;
    private ArrayList<String> names = new ArrayList<>();

    public String getNumberLineMetro() {
        return numberLineMetro;
    }

    public void setNumberLineMetro(String numberLineMetro) {
        this.numberLineMetro = numberLineMetro;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(String name) {
        names.add(name);
    }

    @Override
    public String toString() {
        return
                "Number line metro \"" + getNumberLineMetro() + "\"" +
                "List names stations \"" + getNames() + "\"";

    }
}
