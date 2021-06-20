package application.file;

public class FilePath {

    private final String value;

    private FilePath(String value) {
        this.value = value;
    }

    public static FilePath create(String value) {
        return new FilePath(value);
    }

    public String getValue() {
        return value;
    }
}
