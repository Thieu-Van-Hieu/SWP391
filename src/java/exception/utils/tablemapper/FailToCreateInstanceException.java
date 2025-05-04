package exception.utils.tablemapper;

public class FailToCreateInstanceException extends RuntimeException {

    public FailToCreateInstanceException(Object object) {
        super(String.format("Fail to create instance of Object %s", object.getClass().getName()));
    }
}
