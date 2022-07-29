package com.mango.anosk.mosk;

import com.mango.anosk.mosk.blocks.Blocks;
import com.mango.anosk.mosk.blocks.entity.BlockEntities;
import com.mango.anosk.mosk.config.MoskConfig;
import com.mango.anosk.mosk.items.Items;
import com.mango.anosk.mosk.world.biome.ModBiomes;
import com.mango.anosk.mosk.world.feature.ModConfiguredFeatures;
import com.mango.anosk.mosk.world.gen.ModWorldGen;
import com.mango.anosk.mosk.world.portals.CryingObsidianPortal;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mosk implements ModInitializer {
	public static final String MOD_ID = "mosk";
	public static final Logger LOGGER = LoggerFactory.getLogger("mosk");

	public static void log(String in) {
		LOGGER.info(in);
	}




	@Override
	public void onInitialize() {
		MoskConfig.registerConfigs();
		ModConfiguredFeatures.registerConfiguredFeatures();
		LOGGER.info("Initializing Mosk..");
		Items.initItems();
		Blocks.initBlocks();
		BlockEntities.InitEntities();
		ModWorldGen.generateWorldGen();
		ModBiomes.initBiomes();
		CryingObsidianPortal.registerPortal();
	}

}
