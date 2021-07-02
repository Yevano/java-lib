package yevano.reflection;

import java.util.function.Consumer;

import lombok.NonNull;
import lombok.val;

public class ClassRef<ClassType> {
    public static <ClassType> ClassRef<ClassType> of(
        @NonNull ClassLoader classLoader, @NonNull String name
    ) {
        return new ClassRef<>(classLoader, name);
    }

    public static <ClassType> ClassRef<ClassType> of(@NonNull String name) {
        return ClassRef.of(ClassLoader.getSystemClassLoader(), name);
    }

    public static <ClassType> ClassRef<ClassType> of(@NonNull Class<ClassType> classObject) {
        return ClassRef.of(classObject.getClassLoader(), classObject.getName());
    }

    protected final ClassLoader classLoader;
    protected final String name;

    public ClassRef(ClassLoader classLoader, String name) {
        this.classLoader = classLoader;
        this.name = name;
    }

    public <A> FieldRef<ClassType, A> getFieldRef(@NonNull String fieldName) {
        try {
            @SuppressWarnings("unchecked")
            Class<A> fieldType = (Class<A>) this.getRuntimeClass().getField(fieldName).getType();
            return FieldRef.instanceField(this, ClassRef.of(fieldType), fieldName);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(
                String.format(
                    "Field lookup on %s.%s threw an exception.",
                    this.getRuntimeClass().getName(), fieldName
                ),
                e
            );
        }
    }

    public Class<ClassType> getRuntimeClass() {
        try {
            @SuppressWarnings("unchecked")
            val runtimeClass = (Class<ClassType>) classLoader.loadClass(name);
            return runtimeClass;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                String.format(
                    "Class lookup for %s threw an exception.",
                    this.getRuntimeClass().getName()
                ),
                e
            );
        }
    }

    public ClassRef<ClassType> run(Consumer<Class<ClassType>> f) {
        try {
            @SuppressWarnings("unchecked")
            Class<ClassType> classObject = (Class<ClassType>) classLoader.loadClass(name);
            f.accept(classObject);
        } catch (ClassNotFoundException e) { }

        return this;
    }

    public ClassRef<ClassType> ifError(Consumer<ClassNotFoundException> f) {
        try {
            classLoader.loadClass(name);
        } catch (ClassNotFoundException e) {
            f.accept(e);
        }

        return this;
    }
}
