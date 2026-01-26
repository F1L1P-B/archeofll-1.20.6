package net.filipb.archeofll;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.filipb.archeofll.datagen.ModBlockTagProvider;
import net.filipb.archeofll.datagen.ModItemTagProvider;
import net.filipb.archeofll.datagen.ModLootTableProvider;
import net.filipb.archeofll.datagen.ModModelProvider;
import net.filipb.archeofll.datagen.ModRecipeProvider;

public class ArcheoFLLDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}
}
