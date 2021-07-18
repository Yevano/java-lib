package yevano.bukkit.item;

import net.minecraft.server.v1_8_R3.NBTBase;

public abstract class NBTSerialComponent<NBTType extends NBTBase, DataType>
    implements NBTSerial<NBTBase, Object>
{
    private final NBTSerial<NBTBase, Object> parent;

    public abstract DataType thisDeserialize(NBTType tag) throws NBTSerialException;
    public abstract NBTType thisSerialize(DataType value) throws NBTSerialException;

    protected NBTSerialComponent(NBTSerial<NBTBase, Object> parent) {
        this.parent = parent;
    }

    @Override
    public final Object deserialize(NBTBase tag) throws NBTSerialException {
        try {
            return parent.deserialize(tag);
        } catch(NBTSerialException serialException) {
            return thisDeserialize((NBTType) tag);
        }
    }

    @Override
    public final NBTBase serialize(Object value) throws NBTSerialException {
        try {
            return parent.serialize(value);
        } catch(NBTSerialException serialException) {
            return thisSerialize((DataType) value);
        }
    }
}
