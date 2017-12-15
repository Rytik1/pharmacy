package by.onlinepharmacy.exception;


public class BusinessLogicException extends Exception {
    public BusinessLogicException(String message) {
        super(message);
    }

    public BusinessLogicException() {
        super();
    }

    public BusinessLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessLogicException(Throwable cause) {
        super(cause);
    }
}

