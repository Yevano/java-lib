package yevano.resource;

public class ResourceCloseException extends RuntimeException {
    public ResourceCloseException(ResourceCloseException parent, Exception cause) {
        super(parent == null ? "" : parent.getMessage(), cause);
    }

    public ResourceCloseException(Exception cause) {
        this(null, cause);
    }
}
