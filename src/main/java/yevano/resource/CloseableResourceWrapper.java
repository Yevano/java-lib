package yevano.resource;

import java.io.Closeable;
import java.io.IOException;

import lombok.NonNull;

public class CloseableResourceWrapper extends Destructor {
    public static CloseableResourceWrapper of(@NonNull Closeable closeable) {
        return new CloseableResourceWrapper(closeable);
    }

    private Closeable closeable;

    CloseableResourceWrapper(Closeable closeable) {
        this.closeable = closeable;
    }

    @Override
    protected void destroy(Destructor r) throws ResourceCloseException {
        try {
            this.closeable.close();
        } catch (IOException e) {
            throw new ResourceCloseException(e);
        }
    }
}
