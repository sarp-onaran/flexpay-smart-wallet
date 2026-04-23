package service;

/**
 * Custom exception for payment processing failures.
 * Thrown when a payment cannot be completed due to insufficient funds
 * or invalid payment parameters.
 */
public class PaymentException extends Exception {

    private final String errorCode;

    /**
     * Constructs a new PaymentException with a descriptive message and error code.
     *
     * @param message   human-readable description of the error
     * @param errorCode machine-readable error code (e.g., "INSUFFICIENT_BALANCE")
     */
    public PaymentException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Constructs a new PaymentException wrapping another exception.
     *
     * @param message   human-readable description of the error
     * @param errorCode machine-readable error code
     * @param cause     the underlying cause of this exception
     */
    public PaymentException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * Returns the machine-readable error code associated with this exception.
     *
     * @return the error code string
     */
    public String getErrorCode() {
        return errorCode;
    }
}
