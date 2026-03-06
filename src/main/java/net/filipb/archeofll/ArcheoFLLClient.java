package net.filipb.archeofll;

import net.fabricmc.api.ClientModInitializer;
import net.filipb.archeofll.block.entity.ModBlockEntities;
import net.filipb.archeofll.block.entity.renderer.ArtifactDisplayBlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class ArcheoFLLClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(ModBlockEntities.ARTIFACT_DISPLAY_BLOCK_ENTITY,
            ArtifactDisplayBlockEntityRenderer::new);
    }
}
