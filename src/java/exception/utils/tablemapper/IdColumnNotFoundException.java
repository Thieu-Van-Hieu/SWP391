package exception.utils.tablemapper;

public class IdColumnNotFoundException extends RuntimeException {

    public IdColumnNotFoundException() {
        super("Id column not found!!!");
    }
}
