package beans.persoana.exceptions;

public class MyBusinessException extends RuntimeException{
    public MyBusinessException() {
        super("too many persons with same name");
    }
}
