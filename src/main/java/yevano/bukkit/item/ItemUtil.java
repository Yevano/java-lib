package yevano.bukkit.item;

import org.bukkit.persistence.PersistentDataType;

import lombok.NonNull;

public class ItemUtil {
    @SuppressWarnings("all")
    public static <Type> PersistentDataType<Type, Type> getPersistentType(
        @NonNull Class<Type> valueClass
    ) {
        if(valueClass == Byte.TYPE) {
            return (PersistentDataType<Type, Type>) PersistentDataType.BYTE;
        } else if(valueClass == Short.TYPE) {
            return (PersistentDataType<Type, Type>) PersistentDataType.SHORT;
        } else if(valueClass == Integer.TYPE) {
            return (PersistentDataType<Type, Type>) PersistentDataType.INTEGER;
        } else if(valueClass == Long.TYPE) {
            return (PersistentDataType<Type, Type>) PersistentDataType.LONG;
        } else if(valueClass == Float.TYPE) {
            return (PersistentDataType<Type, Type>) PersistentDataType.FLOAT;
        } else if(valueClass == Double.TYPE) {
            return (PersistentDataType<Type, Type>) PersistentDataType.DOUBLE;
        } else if(valueClass == byte[].class) {
            return (PersistentDataType<Type, Type>) PersistentDataType.BYTE_ARRAY;
        } else if(valueClass == int[].class) {
            return (PersistentDataType<Type, Type>) PersistentDataType.INTEGER_ARRAY;
        } else if(valueClass == long[].class) {
            return (PersistentDataType<Type, Type>) PersistentDataType.LONG_ARRAY;
        } else if(valueClass == String.class) {
            return (PersistentDataType<Type, Type>) PersistentDataType.STRING;
        } else if(valueClass.isArray()) {
            return (PersistentDataType<Type, Type>) PersistentDataType.TAG_CONTAINER_ARRAY;
        }

        return (PersistentDataType<Type, Type>) PersistentDataType.TAG_CONTAINER;
    }
}
