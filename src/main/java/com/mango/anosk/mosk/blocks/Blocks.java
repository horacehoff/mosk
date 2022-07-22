package com.mango.anosk.mosk.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks {
    public static final HupperBlock HUPPER_BLOCK = new HupperBlock(FabricBlockSettings.of(Material.METAL).hardness(3.0f).nonOpaque());
    public static final Block BIRCH_MUSHROOM = new Block(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.MOSS_BLOCK));
    public static final MushroomParasiteBlock MUSHROOM_PARASITE_BLOCK = new MushroomParasiteBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC).hardness(0.2f).nonOpaque());
    public static void initBlocks() {
        Registry.register(Registry.BLOCK, new Identifier("mosk", "hupper_block"), HUPPER_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("mosk", "birch_mushroom"), BIRCH_MUSHROOM);
        Registry.register(Registry.BLOCK, new Identifier("mosk", "mushroom_parasite"), MUSHROOM_PARASITE_BLOCK);

    }

}
