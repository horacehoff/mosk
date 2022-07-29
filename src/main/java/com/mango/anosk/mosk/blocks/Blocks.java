package com.mango.anosk.mosk.blocks;

import com.mango.anosk.mosk.Mosk;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public abstract class Blocks {
    public static final HupperBlock HUPPER_BLOCK = new HupperBlock(FabricBlockSettings
            .of(Material.METAL)
            .hardness(3.0f)
            .nonOpaque()
            .resistance(4.8f)
            .sounds(BlockSoundGroup.METAL)
    );
    public static final Block BIRCH_MUSHROOM = new Block(FabricBlockSettings.copyOf(net.minecraft.block.Blocks.MOSS_BLOCK));
    public static final MushroomParasiteBlock MUSHROOM_PARASITE_BLOCK = new MushroomParasiteBlock(FabricBlockSettings
            .of(Material.SOLID_ORGANIC)
            .hardness(0.2f)
            .nonOpaque()
    );

    public static final PulleyBlock PULLEY_BLOCK = new PulleyBlock(FabricBlockSettings
            .of(Material.WOOD)
            .strength(2.0f).nonOpaque());

    public static final RopeBlock ROPE_BLOCK = new RopeBlock(FabricBlockSettings
            .of(Material.BAMBOO)
            .strength(1.0f)
    );

    public static final WickBlock WICK_BLOCK = new WickBlock(FabricBlockSettings
            .of(Material.METAL)
            .strength(0.5f)
    );

    public static void initBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "hupper_block"), HUPPER_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "birch_mushroom"), BIRCH_MUSHROOM);
        Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "mushroom_parasite_block"), MUSHROOM_PARASITE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "pulley_block"), PULLEY_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "rope_block"), ROPE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "wick_block"), WICK_BLOCK);
    }
}
