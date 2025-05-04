package exception.utils.tablemapper;

public class UnsupportedTypeException extends RuntimeException {

    public UnsupportedTypeException(String type) {
        super(String.format("Unsupported type: %s", type));
    }
}
