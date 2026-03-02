package net.filipb.archeofll.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

import net.filipb.archeofll.util.ItemSetter;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.BrushableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin {
    @WrapMethod(method = "onUseWithItem")
    protected ItemActionResult setBrushableBlockItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, Operation<ItemActionResult> original) {
        if ((Object) this instanceof BrushableBlock) {
            if (world.getBlockEntity(pos) instanceof ItemSetter setter
            && setter.archeofll$canSetItem()
            && player.isCreative()) {
                if (world.isClient) {
                    return ItemActionResult.SUCCESS;
                } else {
                    setter.archeofll$setItem(stack.getItem());
                    return ItemActionResult.CONSUME;
                }
            }
        }
        return original.call(stack, state, world, pos, player, hand, hit);
    }
}
