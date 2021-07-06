package yevano.resource;

public interface Resource {
    public void close() throws ResourceCloseException;
}
