package net.filipb.archeofll.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class ArtifactDisplayBlockEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory =
    DefaultedList.ofSize(1, ItemStack.EMPTY);

    public ArtifactDisplayBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ARTIFACT_DISPLAY_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public int getMaxCountPerStack() {
        return 1;
    }

    public ItemStack getRenderStack() {
        return getStack(0);
    }

    @Override
    public void markDirty() {
        if (world != null) {
            world.updateListeners(pos,
                getCachedState(),
                getCachedState(),
                Block.NOTIFY_ALL);
        }
        super.markDirty();
    }

    @Override
    protected void readNbt(NbtCompound nbt, WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        inventory.clear();
        Inventories.readNbt(nbt, inventory, registryLookup);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}
