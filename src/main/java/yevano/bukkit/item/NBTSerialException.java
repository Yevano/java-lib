package yevano.bukkit.item;

import yevano.util.Format;

public class NBTSerialException extends Exception {
    private static final String msgStart = "Could not convert %s(%s) to %s";

    public NBTSerialException(Object from, Class<?> to, String message, Throwable cause) {
        super(Format.string(
            msgStart + ": %s", from.getClass().getName(), from, to.getName(), message
        ), cause);
    }

    public NBTSerialException(Object from, Class<?> to, String message) {
        super(Format.string(
            msgStart + ": %s", from.getClass().getName(), from, to.getName(), message
        ));
    }

    public NBTSerialException(Object from, Class<?> to) {
        super(Format.string(msgStart, from.getClass().getName(), from, to.getName()));
    }
}
