package yevano.bukkit.item;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.val;
import yevano.util.MutRef;
import yevano.util.Pair;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemMaker {
    private final Optional<Integer> amount;
    private final Optional<Material> material;
    private final Optional<String> name;
    private final Optional<ItemStack> copy;
    private final Map<String, Pair<?, PersistentDataType<?, ?>>> attachments;
    private final PersistentDataContainer data;

    public ItemMaker amount(int amount) {
        return new ItemMaker(Optional.of(amount), material, name, copy, attachments, data);
    }

    public ItemMaker material(Material material) {
        return new ItemMaker(amount, Optional.of(material), name, copy, attachments, data);
    }

    public ItemMaker name(String name) {
        return new ItemMaker(amount, material, Optional.of(name), copy, attachments, data);
    }

    public ItemMaker copy(ItemStack copy) {
        return new ItemMaker(amount, material, name, Optional.of(new ItemStack(copy)), attachments, data);
    }

    public <ValueType> ItemMaker attach(@NonNull String key, ValueType value) {
        val attachments = new HashMap<>(this.attachments);
        attachments.put(key, Pair.of(value, ItemUtil.getPersistentType(value.getClass())));
        return new ItemMaker(amount, material, name, copy, attachments, data);
    }

    public ItemMaker serial(PersistentDataContainer data) {
        return new ItemMaker(amount, material, name, copy, attachments, data);
    }

    public ItemStack asItemStack() {
        var itemStack = MutRef.of(new ItemStack(material.orElse(Material.AIR), amount.orElse(1)));

        var itemMeta = itemStack.get().getItemMeta();
        MutRef<PersistentDataContainer> data = MutRef.empty();

        copy.ifPresent(copyItemStack -> {
            if(copyItemStack.hasItemMeta()) {
                var copyMeta = copyItemStack.getItemMeta();
                var copyData = copyMeta.getPersistentDataContainer();
                data.set(copyData);
            }
            itemStack.set(copyItemStack);
        });

        name.ifPresent(name -> {
            if(data.get() == null) data.set(itemMeta.getPersistentDataContainer());
            itemMeta.setDisplayName(name);
        });

        for(val kv : attachments.entrySet()) {
            if(data.get() == null) {
                var copyMeta = itemStack.get().getItemMeta();
                var copyData = copyMeta.getPersistentDataContainer();
                data.set(copyData);
            }
            var key = NamespacedKey.fromString(kv.getKey());
            var pair = kv.getValue();
            pair.run((value, type) -> {
                @SuppressWarnings("all")
                var t = (PersistentDataType<Object, Object>) type;
                data.get().set(key, t, value);
            });
        }

        var result = new ItemStack(itemStack.get());
        result.setItemMeta(itemMeta);
        return result;
    }
}
