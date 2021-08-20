package yevano.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * A set of instances, each of which may be accessed by providing a class from which that instance
 * derives.
 */
public class InstanceSet {
    private final Collection<Object> instances;

    public InstanceSet() {
        this.instances = new ArrayList<>();
    }

    /**
     * <p>Gets the instance which derives most closely from a given class or interface.</p>
     * 
     * <p>More formally, if {@code A extends B extends C extends D}, and {@code b == new B()},
     * {@code c == new C()} are objects in {@code this} {@link InstanceSet}, then
     * {@code this.get(B.class) == b}, {@code this.get(C.class)} == {@code Some(c)}, and
     * {@code this.get(D.class)== c)}. {@code this.get(A.class)} throws an exception, because no
     * instance of {@code A} is present.</p>
     * 
     * Instances are searched for in the same order they were added to {@code this}
     * {@code InstanceSet}. If two or more instances derive from {@code type} at the same exact
     * distance, then the instance which was added first will be selected.
     * 
     * @param <InstanceType> the type of instance to get
     * @param type the {@link Class} to access an instance of
     * @return an instance of the class represented by {@code type}
     * @throws NoSuchElementException if no instance of the class represented by {@code type} is
     * present.
     */
    @SuppressWarnings("all")
    public <InstanceType> InstanceType get(Class<InstanceType> type) {
        var maxDistance = Integer.MAX_VALUE;
        InstanceType result = null;

        for(var instance : instances) {
            var distance = derivationDistance(type, instance.getClass());
            if(distance > maxDistance || distance == -1) continue;
            if(distance == 0) return (InstanceType) instance;
            result = (InstanceType) instance;
        }

        if(result == null) throw new NoSuchElementException(
            Format.stringf("No instance of %s found in this InstanceSet.", type.getName())
        );

        return result;
    }

    /**
     * Adds an instance to {@code this} {@link InstanceSet}.
     * 
     * @param instance the instance to add
     * @return {@code this} {@code InstanceSet}
     */
    public InstanceSet add(Object instance) {
        this.instances.add(instance);
        return this;
    }

    private int derivationDistance(Class<?> superClass, Class<?> derivedClass) {
        if(superClass.equals(derivedClass)) return 0; // Classes are exactly the same.
        if(!superClass.isAssignableFrom(derivedClass)) return -1; // Relationship does not exist.

        var implementedInterfaces = derivedClass.getInterfaces();

        // For each interface that derivedClass implements, check if that interface derives from
        // superClass.
        for(var implementedInterface : implementedInterfaces) {
            if(superClass.isAssignableFrom(implementedInterface)) {
                return derivationDistance(superClass, implementedInterface) + 1;
            }
        }

        return derivationDistance(superClass, derivedClass.getSuperclass()) + 1;
    }
}
