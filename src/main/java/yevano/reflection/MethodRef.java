package yevano.reflection;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;

import lombok.NonNull;
import lombok.val;

public class MethodRef<ClassType, ReturnType> {
    protected final ClassRef<ClassType> classRef;
    protected final String name;
    protected final MethodHandle getterHandle;
    protected final boolean instanceRef;

    public static <A, B> MethodRef<A, B> instance(
        @NonNull ClassRef<A> holdingClassRef, @NonNull ClassRef<B> returnTypeRef,
        @NonNull String name
    ) {
        Class<A> runtimeClass = holdingClassRef.getRuntimeClass();
        MethodType methodType = MethodType.methodType(runtimeClass, Object[].class);
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        try {
            for(val method : runtimeClass.getDeclaredMethods()) {
                if(method.getName().equals(name)) {
                    method.setAccessible(true);
                }
            }

            MethodHandle getterHandle = lookup.findVirtual(runtimeClass, name, methodType);
            return new MethodRef<>(holdingClassRef, getterHandle, name, true);
        } catch (IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(
                String.format(
                    "Method lookup for %s.%s threw an exception.",
                    holdingClassRef.getRuntimeClass().getName(), name
                ),
                e
            );
        }
    }

    public static <A, B> MethodRef<A, B> statically(
        @NonNull ClassRef<A> holdingClassRef, @NonNull ClassRef<B> returnTypeRef,
        @NonNull String name
    ) {
        Class<A> runtimeClass = holdingClassRef.getRuntimeClass();
        MethodType methodType = MethodType.methodType(runtimeClass, Object[].class);
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        try {
            for(val method : runtimeClass.getDeclaredMethods()) {
                if(method.getName().equals(name)) {
                    method.setAccessible(true);
                }
            }

            MethodHandle getterHandle = lookup.findStatic(runtimeClass, name, methodType);
            return new MethodRef<>(holdingClassRef, getterHandle, name, false);
        } catch (IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(
                String.format(
                    "Method lookup for %s.%s threw an exception.",
                    holdingClassRef.getRuntimeClass().getName(), name
                ),
                e
            );
        }
    }

    protected MethodRef(ClassRef<ClassType> classRef, MethodHandle getterHandle, String name, boolean instanceRef) {
        this.classRef = classRef;
        this.getterHandle = getterHandle;
        this.name = name;
        this.instanceRef = instanceRef;
    }

    public ReturnType call(Object... args) {
        try {
            if(instanceRef) {
                Object[] actualArgs = Arrays.copyOfRange(args, 1, args.length);

                @SuppressWarnings("unchecked")
                val result = (ReturnType) getterHandle
                    .bindTo(args[0])
                    .invokeWithArguments(actualArgs)
                ;
                return result;
            } else {
                @SuppressWarnings("unchecked")
                val result = (ReturnType) getterHandle.invokeWithArguments(args);
                return result;
            }
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

    public <T> MethodRef<ClassType, ReturnType> bind(T argument) {
        Class<ClassType> runtimeClass = this.classRef.getRuntimeClass();
        MethodType methodType = MethodType.methodType(runtimeClass, Object[].class);
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        try {
            MethodHandle boundHandle = lookup.findVirtual(runtimeClass, name, methodType)
                .bindTo(argument)
            ;
            return new MethodRef<>(classRef, boundHandle, name, instanceRef);
        } catch (IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(
                String.format(
                    "Method lookup for %s.%s threw an exception.",
                    classRef.getRuntimeClass().getName(), name
                ),
                e
            );
        }
    }
}
