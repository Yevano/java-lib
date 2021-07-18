package yevano.bukkit.item;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import lombok.NonNull;
import lombok.val;
import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagByte;
import net.minecraft.server.v1_8_R3.NBTTagByteArray;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagDouble;
import net.minecraft.server.v1_8_R3.NBTTagFloat;
import net.minecraft.server.v1_8_R3.NBTTagInt;
import net.minecraft.server.v1_8_R3.NBTTagIntArray;
import net.minecraft.server.v1_8_R3.NBTTagList;
import net.minecraft.server.v1_8_R3.NBTTagLong;
import net.minecraft.server.v1_8_R3.NBTTagShort;
import net.minecraft.server.v1_8_R3.NBTTagString;
import yevano.util.Format;

public interface ItemUtil extends NBTSerial<NBTBase, Object>, Format.Context {
    public static final ItemUtil get = new ItemUtil() { };

    default Object deserialize(NBTBase tag) throws NBTSerialException {
        if(tag instanceof NBTTagByte) return deserialize((NBTTagByte) tag);
        if(tag instanceof NBTTagShort) return deserialize((NBTTagShort) tag);
        if(tag instanceof NBTTagInt) return deserialize((NBTTagInt) tag);
        if(tag instanceof NBTTagLong) return deserialize((NBTTagLong) tag);
        if(tag instanceof NBTTagFloat) return deserialize((NBTTagFloat) tag);
        if(tag instanceof NBTTagDouble) return deserialize((NBTTagDouble) tag);
        if(tag instanceof NBTTagByteArray) return deserialize((NBTTagByteArray) tag);
        if(tag instanceof NBTTagIntArray) return deserialize((NBTTagIntArray) tag);
        if(tag instanceof NBTTagString) return deserialize((NBTTagString) tag);
        if(tag instanceof NBTTagList) return deserialize((NBTTagList) tag);
        if(tag instanceof NBTTagCompound) return deserialize((NBTTagCompound) tag);

        throw new NBTSerialException(tag, Object.class);
    }

    default byte deserialize(NBTTagByte tag) {
        return tag.f();
    }

    default short deserialize(NBTTagShort tag) {
        return tag.e();
    }

    default int deserialize(NBTTagInt tag) {
        return tag.d();
    }

    default long deserialize(NBTTagLong tag) {
        return tag.c();
    }

    default float deserialize(NBTTagFloat tag) {
        return tag.h();
    }

    default double deserialize(NBTTagDouble tag) {
        return tag.g();
    }

    default byte[] deserialize(NBTTagByteArray tag) {
        return tag.c();
    }

    default int[] deserialize(NBTTagIntArray tag) {
        return tag.c();
    }

    default String deserialize(NBTTagString tag) throws NBTSerialException {
        return tag.a_();
    }

    default List<?> deserialize(NBTTagList tag) throws NBTSerialException {
        val size = tag.size();
        val result = Lists.newArrayListWithCapacity(size);
        for(int i = 0; i < size; i++) {
            try {
                result.add(deserialize(tag.g(i)));
            } catch (NBTSerialException e) {
                throw new NBTSerialException(tag, Map.class, stringf("Failed on index %s", i), e);
            }
        }
        return result;
    }

    default Map<String, ?> deserialize(NBTTagCompound tag) throws NBTSerialException {
        Map<String, Object> result = Maps.newHashMap();
        for(val key : tag.c()) {
            try {
                result.put(key, deserialize(tag.get(key)));
            } catch (NBTSerialException e) {
                throw new NBTSerialException(tag, Map.class, stringf("Failed on key %s", key), e);
            }
        }
        return result;
    }



    default NBTBase serialize(Object value) throws NBTSerialException {
        if(value instanceof Boolean) return serialize((boolean) value);
        if(value instanceof Byte) return serialize((byte) value);
        if(value instanceof Short) return serialize((short) value);
        if(value instanceof Integer) return serialize((int) value);
        if(value instanceof Long) return serialize((long) value);
        if(value instanceof Float) return serialize((float) value);
        if(value instanceof Double) return serialize((double) value);
        if(value instanceof byte[]) return serialize((byte[]) value);
        if(value instanceof int[]) return serialize((int[]) value);
        if(value instanceof String) return serialize(((String) value).getBytes());
        if(value instanceof List<?>) return serialize((List<?>) value);
        if(value instanceof Map<?, ?>) return serialize((Map<?, ?>) value);

        throw new NBTSerialException(value, NBTBase.class);
    }

    default NBTTagByte serialize(boolean value) {
        return serialize(value ? (byte) 1 : (byte) 0);
    }

    default NBTTagByte serialize(byte value) {
        return new NBTTagByte(value);
    }

    default NBTTagShort serialize(short value) {
        return new NBTTagShort(value);
    }

    default NBTTagInt serialize(int value) {
        return new NBTTagInt(value);
    }

    default NBTTagLong serialize(long value) {
        return new NBTTagLong(value);
    }

    default NBTTagFloat serialize(float value) {
        return new NBTTagFloat(value);
    }

    default NBTTagDouble serialize(double value) {
        return new NBTTagDouble(value);
    }

    default NBTTagByteArray serialize(byte[] value) {
        return new NBTTagByteArray(Arrays.copyOf(value, value.length));
    }

    default NBTTagIntArray serialize(int[] value) {
        return new NBTTagIntArray(Arrays.copyOf(value, value.length));
    }

    default NBTTagList serialize(List<?> value) throws NBTSerialException {
        val result = new NBTTagList();
        for(int i = 0; i < value.size(); i++) {
            val e = value.get(i);

            try {
                result.add(serialize(e));
            } catch (NBTSerialException serialException) {
                throw new NBTSerialException(value, NBTTagList.class,
                    stringf("Failed on index %", i), serialException
                );
            }
        }
        return result;
    }

    default NBTTagCompound serialize(Map<?, ?> value) throws NBTSerialException {
        NBTTagCompound tag = new NBTTagCompound();
        for(val kv : value.entrySet()) {
            try {
                tag.set((String) kv.getKey(), serialize(kv.getValue()));
            } catch (NBTSerialException serialException) {
                throw new NBTSerialException(value, NBTTagCompound.class,
                    stringf("Failed on key %", kv.getKey()), serialException
                );
            }
        }
        return tag;
    }

    default ItemStack getModifiedNMS(
        @NonNull ItemStack itemStack, Consumer<net.minecraft.server.v1_8_R3.ItemStack> consumer
    ) {
        val nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        consumer.accept(nmsItemStack);
        return CraftItemStack.asBukkitCopy(nmsItemStack);
    }

    default Optional<NBTTagCompound> getTag(@NonNull ItemStack itemStack) {
        val nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        if(nmsItemStack.hasTag()) return Optional.of(nmsItemStack.getTag());
        return Optional.empty();
    }

    default ItemStack getItemStackWithNBT(
        @NonNull ItemStack itemStack, @NonNull NBTTagCompound tag
    ) {
        return getModifiedNMS(itemStack, nmsItemStack -> {
            nmsItemStack.setTag(tag);
        });
    }
}
