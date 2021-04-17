package exception;

public class ProcessingFailureException extends RuntimeException {
    public ProcessingFailureException(String message) {
        super(message);
    }
}
