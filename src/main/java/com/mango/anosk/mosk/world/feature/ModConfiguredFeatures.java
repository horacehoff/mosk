package com.mango.anosk.mosk.world.feature;

import com.mango.anosk.mosk.Mosk;
import com.mango.anosk.mosk.world.feature.tree.BranchTreeDecorator;
import com.mango.anosk.mosk.world.feature.tree.MushroomTreeDecorator;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.treedecorator.TrunkVineTreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    private static final BeehiveTreeDecorator BEES = new BeehiveTreeDecorator(0.05f);
    private static final BranchTreeDecorator BRANCH = new BranchTreeDecorator(0.75f);
    private static final MushroomTreeDecorator MUSHROOM = new MushroomTreeDecorator(0.55f);

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> NEW_BIRCH_TREE =
            ConfiguredFeatures.register("new_birch_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(Blocks.BIRCH_LOG),
                    new StraightTrunkPlacer(6, 7, 5),
                    BlockStateProvider.of(Blocks.BIRCH_LEAVES),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),
                    new TwoLayersFeatureSize(1, 0, 2)).decorators(List.of(BEES, BRANCH, MUSHROOM)).build());

    public static final RegistryEntry<PlacedFeature> NEW_BIRCH_TREE_CHECKED =
            PlacedFeatures.register("new_birch_tree_checked", NEW_BIRCH_TREE,
                    PlacedFeatures.wouldSurvive(Blocks.BIRCH_SAPLING));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> NEW_BIRCH_TREE_SPAWN =
            ConfiguredFeatures.register("new_birch_tree_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(NEW_BIRCH_TREE_CHECKED, 0.5f)),
                            NEW_BIRCH_TREE_CHECKED));

    public static void registerConfiguredFeatures() {
        System.out.println("Registering ModConfiguredFeatures for " + Mosk.MOD_ID);
    }
}
