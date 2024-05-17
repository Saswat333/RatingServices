package exception;

public class EmptyOptionsValue extends Exception {
    public EmptyOptionsValue(String errorMessage){
        super(errorMessage);
    }
}
