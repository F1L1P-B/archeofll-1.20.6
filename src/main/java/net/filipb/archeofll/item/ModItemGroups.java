package net.filipb.archeofll.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.filipb.archeofll.ArcheoFLL;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup ARCHEOFLL_GROUP = Registry.register(Registries.ITEM_GROUP,
        new Identifier(ArcheoFLL.MOD_ID, "archeofll"),
        FabricItemGroup.builder().displayName(Text.translatable("itemgroup.archeofll"))
        .icon(() -> new ItemStack(Items.BRUSH)).entries((displayContext, entries) -> {
            entries.add(Items.BRUSH);

            entries.add(ModItems.CANOPIC_JAR);
            entries.add(ModItems.USHABTI);
            entries.add(ModItems.SCARAB_AMULET);
            entries.add(ModItems.EYE_OF_HORUS);
            entries.add(ModItems.CARTOUCHE_FRAGMENT);
        }).build());

    public static void registerItemGroups() {
        ArcheoFLL.LOGGER.info("Registering Item Groups for " + ArcheoFLL.MOD_ID);
    }
}
