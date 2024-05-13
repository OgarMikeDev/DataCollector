import java.util.ArrayList;
import java.util.List;

public class StationMetro {
    private static ArrayList<String> name = new ArrayList<>();
    private static ArrayList<String> numberLineMetro  = new ArrayList<>();

    public ArrayList<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name.add(name);
    }

    public ArrayList<String> getNumberLineMetro() {
        return numberLineMetro;
    }

    public void setNumberLineMetro(String numberLineMetro) {
        this.numberLineMetro.add(numberLineMetro);
    }
}
