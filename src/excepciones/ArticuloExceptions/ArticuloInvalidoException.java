package excepciones.ArticuloExceptions;

public class ArticuloInvalidoException extends RuntimeException{
    public ArticuloInvalidoException (String msg){
	super(msg);
    }
}
