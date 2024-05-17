public record FromCsvToJava(String name, String date) {
    @Override
    public String toString() {

        return "From csv to java:\n" +
                "\tName \"" + name + "\"," +
                "\n\tDate \"" + date + "\".\n";
    }
}
