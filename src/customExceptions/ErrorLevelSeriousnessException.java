package customExceptions;

public class ErrorLevelSeriousnessException extends Exception {
    
    public ErrorLevelSeriousnessException(){
        super("El nivel de severiedad seleccionado no existe");
    }
}
