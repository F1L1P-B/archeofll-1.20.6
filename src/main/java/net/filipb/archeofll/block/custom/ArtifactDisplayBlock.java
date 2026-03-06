package net.filipb.archeofll.block.custom;

import com.mojang.serialization.MapCodec;

import net.filipb.archeofll.block.entity.ArtifactDisplayBlockEntity;
import net.filipb.archeofll.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ArtifactDisplayBlock extends BlockWithEntity {
    private static final VoxelShape SHAPE =
    Block.createCuboidShape(0, 0, 0, 16, 15, 16);

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
       return createCodec(ArtifactDisplayBlock::new);
    }

    public ArtifactDisplayBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ArtifactDisplayBlockEntity(pos, state);
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ArtifactDisplayBlockEntity) {
                ItemScatterer.spawn(world, pos, (ArtifactDisplayBlockEntity)blockEntity);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
            PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof ArtifactDisplayBlockEntity artifactDisplayBe) {
            if (!stack.isIn(ModTags.Items.ARTIFACTS)
                || !artifactDisplayBe.getStack(0).isEmpty()) {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            } else {
                if (!world.isClient) {
                    artifactDisplayBe.setStack(0, stack.split(1));
                    return ItemActionResult.CONSUME;
                }
                return ItemActionResult.SUCCESS;
            }
        } else {
            return ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof ArtifactDisplayBlockEntity artifactDisplayBe) {
            if (artifactDisplayBe.getStack(0).isEmpty()) {
                return ActionResult.CONSUME;
            } else {
                if (!world.isClient) {
                    ItemStack itemStack = artifactDisplayBe.removeStack(0).copy();
                    if (!player.giveItemStack(itemStack)) {
                        player.dropItem(itemStack, false);
                    }
                    return ActionResult.CONSUME;
                }
                return ActionResult.SUCCESS;
            }
        } else {
            return ActionResult.PASS;
        }
    }
}
