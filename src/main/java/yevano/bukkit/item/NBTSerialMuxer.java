package yevano.bukkit.item;

import java.util.Arrays;

import lombok.NonNull;
import lombok.val;
import net.minecraft.server.v1_8_R3.NBTBase;

public class NBTSerialMuxer implements NBTSerial<NBTBase, Object> {
    private final NBTSerial<NBTBase, Object>[] serials;

    @SuppressWarnings("unchecked")
    public static NBTSerialMuxer of(@NonNull NBTSerial<NBTBase, Object>... serials) {
        val serialsCopy = Arrays.copyOf(serials, serials.length + 1);
        serialsCopy[serials.length] = ItemUtil.get;
        return new NBTSerialMuxer(serialsCopy);
    }

    @SuppressWarnings("unchecked")
    public static NBTSerialMuxer withoutDefault(@NonNull NBTSerial<NBTBase, Object>... serials) {
        val serialsCopy = Arrays.copyOf(serials, serials.length);
        return new NBTSerialMuxer(serialsCopy);
    }

    @SuppressWarnings("unchecked")
    NBTSerialMuxer(NBTSerial<NBTBase, Object>... serials) {
        this.serials = serials;
    }

    @Override
    public Object deserialize(NBTBase tag) throws NBTSerialException {
        for(int i = 0; i < serials.length; i++) {
            try {
                return serials[i].deserialize(tag);
            } catch(NBTSerialException serialException) { }
        }

        throw new NBTSerialException(tag, Object.class);
    }

    @Override
    public NBTBase serialize(Object value) throws NBTSerialException {
        for(int i = 0; i < serials.length; i++) {
            try {
                return serials[i].serialize(value);
            } catch(NBTSerialException serialException) { }
        }

        throw new NBTSerialException(value, NBTBase.class);
    }
}
