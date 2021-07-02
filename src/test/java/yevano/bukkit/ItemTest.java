package yevano.bukkit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.minecraft.server.v1_8_R3.NBTTagInt;
import yevano.bukkit.item.ItemUtil;

public class ItemTest {
    @Test
    public void itemTest() {
        assertEquals(ItemUtil.get.serialize(1).getClass(), NBTTagInt.class);
    }
}
