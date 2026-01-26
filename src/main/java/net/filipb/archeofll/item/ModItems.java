package net.filipb.archeofll.item;

import net.filipb.archeofll.ArcheoFLL;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ArcheoFLL.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ArcheoFLL.LOGGER.info("Registering Mod Items for " + ArcheoFLL.MOD_ID);
    }
}
