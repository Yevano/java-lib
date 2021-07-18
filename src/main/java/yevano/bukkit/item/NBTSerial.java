package yevano.bukkit.item;

import net.minecraft.server.v1_8_R3.NBTBase;

public interface NBTSerial<NBTType extends NBTBase, DataType> {
    DataType deserialize(NBTType tag) throws NBTSerialException;
    NBTType serialize(DataType value) throws NBTSerialException;
}
