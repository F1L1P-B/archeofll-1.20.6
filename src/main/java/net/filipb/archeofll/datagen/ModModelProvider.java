package net.filipb.archeofll.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.filipb.archeofll.block.ModBlocks;
import net.filipb.archeofll.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleState(ModBlocks.ARTIFACT_DISPLAY);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.CANOPIC_JAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.USHABTI, Models.GENERATED);
        itemModelGenerator.register(ModItems.SCARAB_AMULET, Models.GENERATED);
        itemModelGenerator.register(ModItems.EYE_OF_HORUS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CARTOUCHE_FRAGMENT, Models.GENERATED);
    }
}
