package net.filipb.archeofll;

import net.fabricmc.api.ModInitializer;
import net.filipb.archeofll.block.ModBlocks;
import net.filipb.archeofll.item.ModItemGroups;
import net.filipb.archeofll.item.ModItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArcheoFLL implements ModInitializer {
	public static final String MOD_ID = "archeofll";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}