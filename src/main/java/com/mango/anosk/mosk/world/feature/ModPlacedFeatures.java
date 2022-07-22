package com.mango.anosk.mosk.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class ModPlacedFeatures {
    public static final RegistryEntry<PlacedFeature> NEW_BIRCH_TREE_PLACED = PlacedFeatures.register("new_birch_tree_placed",
            ModConfiguredFeatures.NEW_BIRCH_TREE_SPAWN, VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(7, 1f, 2
                    )));
}