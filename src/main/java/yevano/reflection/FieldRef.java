package yevano.reflection;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import lombok.Getter;
import lombok.NonNull;

public class FieldRef<ClassType, FieldType> {
    protected final ClassRef<ClassType> classRef;
    @Getter protected final String name;
    protected final MethodHandle getterHandle;

    public static <A, B> FieldRef<A, B> of(
        @NonNull ClassRef<A> holdingClassRef, @NonNull ClassRef<B> fieldTypeRef,
        @NonNull String name
    ) {
        Class<A> runtimeClass = holdingClassRef.getRuntimeClass();
        Class<B> runtimeFieldType = fieldTypeRef.getRuntimeClass();
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        try {
            Field field = runtimeClass.getDeclaredField(name);
            field.setAccessible(true);
            boolean fieldIsStatic = (field.getModifiers() & Modifier.STATIC) != 0;
            MethodHandle getterHandle = null;
            
            if(fieldIsStatic) {
                getterHandle = lookup.findStaticGetter(runtimeClass, name, runtimeFieldType);
            } else {
                getterHandle = lookup.findGetter(runtimeClass, name, runtimeFieldType);
            }

            return new FieldRef<>(holdingClassRef, getterHandle, name);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(
                String.format(
                    "Field lookup on %s.%s threw an exception.",
                    holdingClassRef.getRuntimeClass().getName(), name
                ),
                e
            );
        }
    }

    protected FieldRef(ClassRef<ClassType> classRef, MethodHandle getterHandle, String name) {
        this.classRef = classRef;
        this.getterHandle = getterHandle;
        this.name = name;
    }

    public FieldType get(@NonNull ClassType instance) {
        try {
            return (FieldType) getterHandle.invoke(instance);
        } catch (Throwable e) {
            throw new RuntimeException(
                String.format(
                    "Field access to %s.%s threw an exception.",
                    classRef.getRuntimeClass().getName(), name
                ),
                e
            );
        }
    }

    public FieldType get() {
        return get(null);
    }
}
