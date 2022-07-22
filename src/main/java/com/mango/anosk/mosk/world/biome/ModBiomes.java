package com.mango.anosk.mosk.world.biome;

import com.mango.anosk.mosk.Mosk;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.OverworldBiomeCreator;


public class ModBiomes {
    public static final RegistryKey<Biome> HIGH_BIRCH_FOREST = RegistryKey.of(Registry.BIOME_KEY, new Identifier(Mosk.MOD_ID, "high_birch_forest"));


    public static void initBiomes() {
        Registry.register(BuiltinRegistries.BIOME, HIGH_BIRCH_FOREST.getValue(), OverworldBiomeCreator.createPlains(false, false, false));
    }
}
