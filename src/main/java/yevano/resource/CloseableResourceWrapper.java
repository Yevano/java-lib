package yevano.resource;

import java.io.Closeable;
import java.io.IOException;

public class CloseableResourceWrapper extends Resource {
    private Closeable closeable;

    public CloseableResourceWrapper(Closeable closeable) {
        super();
        this.closeable = closeable;
    }

    @Override
    public void destroy() throws ResourceCloseException {
        try {
            this.closeable.close();
        } catch (IOException e) {
            throw new ResourceCloseException(e);
        }
    }
}
