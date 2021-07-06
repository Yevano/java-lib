package yevano.resource;

public class ResourceCloseException extends RuntimeException {
    public ResourceCloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceCloseException(Throwable cause) {
        this("An exception occurred while closing a resource.", cause);
    }

    public ResourceCloseException(String message) {
        super(message);
    }
}
