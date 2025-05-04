package exception.utils.tablemapper;

public class ObjectNotFoundException extends RuntimeException {
    
    public ObjectNotFoundException(Class<?> clazz) {
        super(String.format("Object %s not found!!!", clazz.getName()));
    }
}
