package net.filipb.archeofll.block;

import net.filipb.archeofll.ArcheoFLL;
import net.filipb.archeofll.block.custom.ArtifactDisplayBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block ARTIFACT_DISPLAY = registerBlock("artifact_display",
    new ArtifactDisplayBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_DIORITE).nonOpaque()));
    
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(ArcheoFLL.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(ArcheoFLL.MOD_ID, name),
        new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        ArcheoFLL.LOGGER.info("Registering Mod Blocks for " + ArcheoFLL.MOD_ID);
    }
}
