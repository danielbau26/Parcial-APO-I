package customExceptions;

public class IncorrectVersionException extends Exception {

    public IncorrectVersionException(){
        super("Versión digitada incorrectamente. La versión debe ser  (A.B.C)");
    }
    
}
