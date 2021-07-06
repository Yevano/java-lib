package yevano.resource;

public abstract class Destructor implements Resource {
    abstract void destroy(Destructor resource);

    public final void close() {
        try {
            this.destroy(this);
        } catch(Exception e) {
            throw new ResourceCloseException(
                String.format("Exception occurred while closing %s", this), e
            );
        }
    }
}
