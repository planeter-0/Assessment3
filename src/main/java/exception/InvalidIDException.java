package exception;

public class InvalidIDException extends RuntimeException{
    public InvalidIDException(){

    }

    public InvalidIDException(String message){
        super(message);
    }
}
