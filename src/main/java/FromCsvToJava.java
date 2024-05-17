import java.time.LocalDate;

public record FromCsvToJava(String name, LocalDate date) {
    @Override
    public String toString() {

        return "From csv to java:\n" +
                "\tName \"" + name + "\"," +
                "\n\tDate \"" + date + "\".\n";
    }
}
