public class FieldNotFoundException extends RuntimeException{
    private final String errorMessage;

    FieldNotFoundException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getMessage(){
        return errorMessage;
    }
}
