package net.filipb.archeofll.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.filipb.archeofll.util.ItemSetter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;

@Mixin(BrushableBlockEntity.class)
public abstract class BrushableBlockEntityMixin extends BlockEntity implements ItemSetter {
    public BrushableBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Shadow private ItemStack item;
    @Shadow @Nullable private RegistryKey<LootTable> lootTable;
    
    @Override
    public void archeofll$setItem(Item item) {
        this.item = new ItemStack(item);
        this.lootTable = null;
        
        this.markDirty();
        if (this.world != null && !this.world.isClient) {
            this.world.updateListeners(this.getPos(),
                this.getCachedState(),
                this.getCachedState(),
                Block.NOTIFY_ALL);
        }
    }

    @Override
    public boolean archeofll$canSetItem() {
        return (this.item.isEmpty() && this.lootTable == null);
    }
}
