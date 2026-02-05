package net.filipb.archeofll.item;

import net.filipb.archeofll.ArcheoFLL;
import net.filipb.archeofll.item.custom.ArtifactItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CANOPIC_JAR = registerItem("canopic_jar",
        new ArtifactItem(new Item.Settings().maxCount(1)));
    public static final Item USHABTI = registerItem("ushabti",
        new ArtifactItem(new Item.Settings().maxCount(1)));
    public static final Item SCARAB_AMULET = registerItem("scarab_amulet",
        new ArtifactItem(new Item.Settings().maxCount(1)));
    public static final Item EYE_OF_HORUS = registerItem("eye_of_horus",
        new ArtifactItem(new Item.Settings().maxCount(1)));
    public static final Item CARTOUCHE_FRAGMENT = registerItem("cartouche_fragment",
        new ArtifactItem(new Item.Settings().maxCount(1)));
    
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ArcheoFLL.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ArcheoFLL.LOGGER.info("Registering Mod Items for " + ArcheoFLL.MOD_ID);
    }
}
