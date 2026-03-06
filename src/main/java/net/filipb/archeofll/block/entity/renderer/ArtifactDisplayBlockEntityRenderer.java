package net.filipb.archeofll.block.entity.renderer;

import net.filipb.archeofll.block.entity.ArtifactDisplayBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class ArtifactDisplayBlockEntityRenderer implements BlockEntityRenderer<ArtifactDisplayBlockEntity> {
    public ArtifactDisplayBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(ArtifactDisplayBlockEntity entity, float tickDelta, MatrixStack matrices,
            VertexConsumerProvider vertexConsumers, int light, int overlay) {
        World world = entity.getWorld();
        if (world == null) return;

        ItemStack itemStack = entity.getRenderStack();
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        int lightLevel = getLightLevel(world, entity.getPos());
        long time = world.getTime();

        matrices.push();
        matrices.translate(0.5f, 0, 0.5f);
        for (int i = 0; i < 4; i++) {
            matrices.push();
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90 * i));
            matrices.translate(0, 0.46875f, 0.376);
            matrices.scale(0.4f, 0.4f, 0.5f);

            itemRenderer.renderItem(itemStack,
                ModelTransformationMode.FIXED,
                lightLevel, 
                OverlayTexture.DEFAULT_UV,
                matrices, vertexConsumers,
                world, 1);
            matrices.pop();
        }
        matrices.pop();

        matrices.push();
        double offset = Math.sin((time + tickDelta) / 8.0) / 8.0;
        matrices.translate(0.5f, 1.4 + offset, 0.5f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((time + tickDelta) * 4));
        matrices.scale(0.7f, 0.7f, 1.4f);

        itemRenderer.renderItem(itemStack,
            ModelTransformationMode.FIXED,
            lightLevel, 
            OverlayTexture.DEFAULT_UV,
            matrices, vertexConsumers,
            world, 1);
        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}
