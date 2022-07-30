package com.mango.anosk.mosk.blocks;

import com.mango.anosk.mosk.Mosk;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public abstract class Blocks {
    public static final HupperBlock HUPPER_BLOCK = new HupperBlock(FabricBlockSettings
            .of(Material.METAL, MapColor.STONE_GRAY)
            .requiresTool()
            .strength(3.0f, 4.8f)
            .sounds(BlockSoundGroup.METAL)
            .nonOpaque());
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

    public static final Block CRACKED_STONE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.STONE_GRAY).requiresTool().strength(2.0f, 6.0f));
    public static final Block CRACKED_STONE_BRICK_STAIRS = new StairsBlock(net.minecraft.block.Blocks.CRACKED_STONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(net.minecraft.block.Blocks.CRACKED_STONE_BRICKS));






    public static final Block WHITE_CONCRETE_SLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "white_concrete_slab"), new SlabBlock(AbstractBlock.Settings.of(Material.STONE, DyeColor.WHITE).requiresTool().strength(1.8f)));
    public static final Block ORANGE_CONCRETE_SLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "orange_concrete_slab"), new SlabBlock(AbstractBlock.Settings.of(Material.STONE, DyeColor.ORANGE).requiresTool().strength(1.8f)));
    public static final Block MAGENTA_CONCRETE_SLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "magenta_concrete_slab"), new SlabBlock(AbstractBlock.Settings.of(Material.STONE, DyeColor.MAGENTA).requiresTool().strength(1.8f)));
    public static final Block LIGHT_BLUE_CONCRETE_SLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "light_blue_concrete_slab"), new SlabBlock(AbstractBlock.Settings.of(Material.STONE, DyeColor.LIGHT_BLUE).requiresTool().strength(1.8f)));
    public static final Block YELLOW_CONCRETE_SLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "yellow_concrete_slab"), new SlabBlock(AbstractBlock.Settings.of(Material.STONE, DyeColor.YELLOW).requiresTool().strength(1.8f)));
    public static final Block LIME_CONCRETE_SLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "lime_concrete_slab"), new SlabBlock(AbstractBlock.Settings.of(Material.STONE, DyeColor.LIME).requiresTool().strength(1.8f)));
    public static final Block PINK_CONCRETE_SLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "pink_concrete_slab"), new SlabBlock(AbstractBlock.Settings.of(Material.STONE, DyeColor.PINK).requiresTool().strength(1.8f)));
    public static final Block GRAY_CONCRETE_SLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "gray_concrete_slab"), new SlabBlock(AbstractBlock.Settings.of(Material.STONE, DyeColor.GRAY).requiresTool().strength(1.8f)));
    public static final Block LIGHT_GRAY_CONCRETE_SLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "light_gray_concrete_slab"), new SlabBlock(AbstractBlock.Settings.of(Material.STONE, DyeColor.LIGHT_GRAY).requiresTool().strength(1.8f)));
    public static final Block CYAN_CONCRETE_SLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "cyan_concrete_slab"), new SlabBlock(AbstractBlock.Settings.of(Material.STONE, DyeColor.CYAN).requiresTool().strength(1.8f)));
    public static final Block PURPLE_CONCRETE_SLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "purple_concrete_slab"), new SlabBlock(AbstractBlock.Settings.of(Material.STONE, DyeColor.PURPLE).requiresTool().strength(1.8f)));
    public static final Block BLUE_CONCRETE_SLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "blue_concrete_slab"), new SlabBlock(AbstractBlock.Settings.of(Material.STONE, DyeColor.BLUE).requiresTool().strength(1.8f)));
    public static final Block BROWN_CONCRETE_SLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "brown_concrete_slab"), new SlabBlock(AbstractBlock.Settings.of(Material.STONE, DyeColor.BROWN).requiresTool().strength(1.8f)));
    public static final Block GREEN_CONCRETE_SLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "green_concrete_slab"), new SlabBlock(AbstractBlock.Settings.of(Material.STONE, DyeColor.GREEN).requiresTool().strength(1.8f)));
    public static final Block RED_CONCRETE_SLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "red_concrete_slab"), new SlabBlock(AbstractBlock.Settings.of(Material.STONE, DyeColor.RED).requiresTool().strength(1.8f)));
    public static final Block BLACK_CONCRETE_SLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "black_concrete_slab"), new SlabBlock(AbstractBlock.Settings.of(Material.STONE, DyeColor.BLACK).requiresTool().strength(1.8f)));



    public static final Block ORANGE_CONCRETE_STAIRS_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "orange_concrete_stairs"), new StairsBlock(net.minecraft.block.Blocks.ORANGE_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(net.minecraft.block.Blocks.ORANGE_CONCRETE)));
    public static final Block MAGENTA_CONCRETE_STAIRS_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "magenta_concrete_stairs"), new StairsBlock(net.minecraft.block.Blocks.MAGENTA_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(net.minecraft.block.Blocks.MAGENTA_CONCRETE)));
    public static final Block LIGHT_BLUE_CONCRETE_STAIRS_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "light_blue_concrete_stairs"), new StairsBlock(net.minecraft.block.Blocks.LIGHT_BLUE_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(net.minecraft.block.Blocks.LIGHT_BLUE_CONCRETE)));
    public static final Block YELLOW_CONCRETE_STAIRS_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "yellow_concrete_stairs"), new StairsBlock(net.minecraft.block.Blocks.YELLOW_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(net.minecraft.block.Blocks.YELLOW_CONCRETE)));
    public static final Block LIME_CONCRETE_STAIRS_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "lime_concrete_stairs"), new StairsBlock(net.minecraft.block.Blocks.LIME_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(net.minecraft.block.Blocks.LIME_CONCRETE)));
    public static final Block PINK_CONCRETE_STAIRS_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "pink_concrete_stairs"), new StairsBlock(net.minecraft.block.Blocks.PINK_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(net.minecraft.block.Blocks.PINK_CONCRETE)));
    public static final Block GRAY_CONCRETE_STAIRS_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "gray_concrete_stairs"), new StairsBlock(net.minecraft.block.Blocks.GRAY_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(net.minecraft.block.Blocks.GRAY_CONCRETE)));
    public static final Block LIGHT_GRAY_CONCRETE_STAIRS_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "light_gray_concrete_stairs"), new StairsBlock(net.minecraft.block.Blocks.LIGHT_GRAY_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(net.minecraft.block.Blocks.LIGHT_GRAY_CONCRETE)));
    public static final Block CYAN_CONCRETE_STAIRS_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "cyan_concrete_stairs"), new StairsBlock(net.minecraft.block.Blocks.CYAN_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(net.minecraft.block.Blocks.CYAN_CONCRETE)));
    public static final Block PURPLE_CONCRETE_STAIRS_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "purple_concrete_stairs"), new StairsBlock(net.minecraft.block.Blocks.PURPLE_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(net.minecraft.block.Blocks.PURPLE_CONCRETE)));
    public static final Block BLUE_CONCRETE_STAIRS_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "blue_concrete_stairs"), new StairsBlock(net.minecraft.block.Blocks.BLUE_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(net.minecraft.block.Blocks.BLUE_CONCRETE)));
    public static final Block BROWN_CONCRETE_STAIRS_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "brown_concrete_stairs"), new StairsBlock(net.minecraft.block.Blocks.BROWN_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(net.minecraft.block.Blocks.BROWN_CONCRETE)));
    public static final Block GREEN_CONCRETE_STAIRS_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "green_concrete_stairs"), new StairsBlock(net.minecraft.block.Blocks.GREEN_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(net.minecraft.block.Blocks.GREEN_CONCRETE)));
    public static final Block RED_CONCRETE_STAIRS_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "red_concrete_stairs"), new StairsBlock(net.minecraft.block.Blocks.RED_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(net.minecraft.block.Blocks.RED_CONCRETE)));
    public static final Block BLACK_CONCRETE_STAIRS_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "black_concrete_stairs"), new StairsBlock(net.minecraft.block.Blocks.BLACK_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(net.minecraft.block.Blocks.BLACK_CONCRETE)));




    public static void initBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "hupper_block"), HUPPER_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "birch_mushroom"), BIRCH_MUSHROOM);
        Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "mushroom_parasite_block"), MUSHROOM_PARASITE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "pulley_block"), PULLEY_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "rope_block"), ROPE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "wick_block"), WICK_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "cracked_stone_brick_slab"), CRACKED_STONE_BRICK_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(Mosk.MOD_ID, "cracked_stone_brick_stairs"), CRACKED_STONE_BRICK_STAIRS);
    }
}
