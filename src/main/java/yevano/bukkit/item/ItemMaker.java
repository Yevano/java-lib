package yevano.bukkit.item;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.val;
import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import yevano.util.MutRef;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemMaker {
    public static final ItemMaker get = new ItemMaker(
        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
        Collections.emptyMap(), ItemUtil.get
    );

    private final Optional<Integer> amount;
    private final Optional<Material> material;
    private final Optional<String> name;
    private final Optional<ItemStack> copy;
    private final Map<String, Object> attachments;
    private final NBTSerial<NBTBase, Object> serial;

    public ItemMaker amount(int amount) {
        return new ItemMaker(Optional.of(amount), material, name, copy, attachments, serial);
    }

    public ItemMaker material(Material material) {
        return new ItemMaker(amount, Optional.of(material), name, copy, attachments, serial);
    }

    public ItemMaker name(String name) {
        return new ItemMaker(amount, material, Optional.of(name), copy, attachments, serial);
    }

    public ItemMaker copy(ItemStack copy) {
        return new ItemMaker(amount, material, name, Optional.of(new ItemStack(copy)), attachments, serial);
    }

    public ItemMaker attach(@NonNull String key, Object object) {
        val attachments = new HashMap<>(this.attachments);
        attachments.put(key, object);
        return new ItemMaker(amount, material, name, copy, attachments, serial);
    }

    public ItemMaker serial(NBTSerial<NBTBase, Object> serial) {
        return new ItemMaker(amount, material, name, copy, attachments, serial);
    }

    public ItemStack asItemStack() throws NBTSerialException {
        val itemUtil = ItemUtil.get;
        val itemStack = MutRef.of(new ItemStack(material.orElse(Material.AIR), amount.orElse(1)));

        MutRef<NBTTagCompound> tag = MutRef.empty();

        copy.ifPresent(copyItemStack -> {
            if(copyItemStack.hasItemMeta()) tag.set(itemUtil.getTag(copyItemStack).get());
            itemStack.set(copyItemStack);
        });

        name.ifPresent(name -> {
            if(tag.get() == null) tag.set(new NBTTagCompound());
            if(!tag.get().hasKey("display")) {
                tag.get().set("display", new NBTTagCompound());
            }

            tag.get().getCompound("display").setString("Name", name);
        });

        for(val kv : attachments.entrySet()) {
            if(tag.get() == null) tag.set(new NBTTagCompound());
            tag.get().set(kv.getKey(), itemUtil.serialize(kv.getValue()));
        }

        return itemUtil.getItemStackWithNBT(itemStack.get(), tag.get());
    }
}
