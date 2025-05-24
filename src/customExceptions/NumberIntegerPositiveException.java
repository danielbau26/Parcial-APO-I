package customExceptions;


public class NumberIntegerPositiveException extends Exception {
    
    public NumberIntegerPositiveException(){
        super("El número serial debe ser entero mayor a 0");
    }
}
