package customExceptions;

public class DateOutOfRangeException extends Exception {
    
    public DateOutOfRangeException(){
        super("La fecha ingresada no esta entre el rango dicho");
    }
}
