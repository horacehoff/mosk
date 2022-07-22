package com.mango.anosk.mosk;

import com.mango.anosk.mosk.blocks.Blocks;
import com.mango.anosk.mosk.blocks.entity.BlockEntities;
import com.mango.anosk.mosk.items.Items;
import com.mango.anosk.mosk.world.biome.ModBiomes;
import com.mango.anosk.mosk.world.feature.ModConfiguredFeatures;
import com.mango.anosk.mosk.world.gen.ModWorldGen;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mosk implements ModInitializer {
	public static final String MOD_ID = "mosk";
	public static final Logger LOGGER = LoggerFactory.getLogger("mosk");

	@Override
	public void onInitialize() {
		ModConfiguredFeatures.registerConfiguredFeatures();
		LOGGER.info("Initializing Mosk..");
		Items.initItems();
		Blocks.initBlocks();
		BlockEntities.InitEntities();
		ModWorldGen.generateWorldGen();
		ModBiomes.initBiomes();

	}

}
