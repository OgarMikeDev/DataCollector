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

    public ArrayList<String> getName() {
        return names;
    }

    public void setName(String name) {
        names.add(name);
    }
}