package com.mango.anosk.mosk.blocks;

import com.mango.anosk.mosk.blocks.HupperBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks {
    public static final HupperBlock HUPPER_BLOCK = new HupperBlock(FabricBlockSettings.of(Material.METAL).hardness(3.0f));
    public static final Block BIRCH_MUSHROOM = new Block(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.MOSS_BLOCK));

    public static void initBlocks() {
        Registry.register(Registry.BLOCK, new Identifier("mosk", "hupper_block"), HUPPER_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("mosk", "birch_mushroom"), BIRCH_MUSHROOM);

    }

}
