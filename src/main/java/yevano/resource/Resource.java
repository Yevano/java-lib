package yevano.resource;

import java.util.Stack;

public class Resource {
    public static final Resource none = new Resource() { };

    private final Stack<Resource> resources;

    protected Resource(Resource... resources) {
        this.resources = new Stack<>();

        for(Resource resource : resources) {
            this.resources.add(resource);
        }
    }

    public void destroy() { }

    public void close() {
        ResourceCloseException exception = null;

        try {
            this.destroy();
        } catch(Exception e) {
            exception = new ResourceCloseException(null, e);
        }

        while(resources.size() > 0) {
            Resource resource = resources.pop();

            try {
                resource.close();
            } catch(Exception e) {
                exception = new ResourceCloseException(exception, e);
            }
        }

        if(exception != null) {
            throw exception;
        }
    }
}
