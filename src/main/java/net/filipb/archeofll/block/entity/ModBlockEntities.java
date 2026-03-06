package net.filipb.archeofll.block.entity;

import net.filipb.archeofll.ArcheoFLL;
import net.filipb.archeofll.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<ArtifactDisplayBlockEntity> ARTIFACT_DISPLAY_BLOCK_ENTITY =
    Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(ArcheoFLL.MOD_ID, "artifact_display_be"),
    BlockEntityType.Builder.create(ArtifactDisplayBlockEntity::new,
    ModBlocks.ARTIFACT_DISPLAY).build());

    public static void registerBlockEntities() {
        ArcheoFLL.LOGGER.info("Registering Block Entities for " + ArcheoFLL.MOD_ID);
    }
}