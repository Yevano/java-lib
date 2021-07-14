package yevano.resource;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

import lombok.NonNull;
import lombok.val;

public abstract class ResourceOwner extends Destructor {
    private Stack<Resource> resources = new Stack<>();

    protected void pushResource(@NonNull Resource resource) {
        resources.push(resource);
    }

    protected void pushResource(@NonNull Closeable closeable) {
        resources.push(CloseableResourceWrapper.of(closeable));
    }

    protected <A> void defer(@NonNull Consumer<A> f, A x) {
        defer(() -> f.accept(x));
    }

    protected void defer(@NonNull Runnable runnable) {
        resources.add(new Destructor() {
            protected void destroy(Destructor resource) {
                runnable.run();
            }
        });
    }

    protected void destroy(ResourceOwner resourceOwner) { }

    @Override
    protected final void destroy(Destructor $) {
        List<Exception> errors = new ArrayList<>();

        try {
            this.destroy(this);
        } catch(Exception e) {
            errors.add(new ResourceCloseException(e));
        }

        Resource owned = null;

        while(resources.size() != 0) {
            try {
                owned = resources.pop();
                owned.close();
            } catch(Exception exception) {
                errors.add(new ResourceCloseException(
                    String.format(
                        "Exception occurred while closing %s, owned by %s.",
                        owned.getClass(), this.getClass()
                    ),
                    exception
                ));
            }
        }

        if(errors.size() == 0) return;

        val out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out, true);
        writer.printf("One or more exceptions occurred while closing %s.", this.getClass());

        for(val e : errors) {
            e.printStackTrace(writer);
        }

        String[] outString = new String(out.toByteArray()).split("\n");
        StringBuilder builder = new StringBuilder();

        for(val line : outString) {
            builder.append("- " + line + "\n");
        }

        throw new ResourceCloseException(builder.toString());
    }
}
