package com.mango.anosk.mosk.items;

import com.mango.anosk.mosk.Mosk;
import com.mango.anosk.mosk.blocks.Blocks;
import com.mango.anosk.mosk.mixin.StatusEffectsMixin;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Items {
    public static final RopeItem ROPE_ITEM = new RopeItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(16));
    public static final BlockItem PULLEY_ITEM = Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "pulley_block"), new BlockItem(Blocks.PULLEY_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));
    public static final Item STONE_BRICK_SLAB = new BlockItem(Blocks.CRACKED_STONE_BRICK_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem HUPPER_ITEM = new BlockItem(Blocks.HUPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem MUSHROOM_PARASITE_ITEM = new BlockItem(Blocks.MUSHROOM_PARASITE_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final BlockItem WICK_BLOCK = new BlockItem(Blocks.WICK_BLOCK, new Item.Settings().group(ItemGroup.MISC));

    public static final Potion LEVITATION = StatusEffectsMixin.invokeRegister("levitation", new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 1800)));
    public static final Potion GLOWING = StatusEffectsMixin.invokeRegister("glowing", new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 2100)));

   
    public static void initItems() {
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "rope_item"), ROPE_ITEM);
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "hupper_block"), HUPPER_ITEM);
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "mushroom_parasite_block"), MUSHROOM_PARASITE_ITEM);
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "wick_block"), WICK_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "cracked_stone_brick_slab"), STONE_BRICK_SLAB);
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "cracked_stone_brick_stairs"), new BlockItem(Blocks.CRACKED_STONE_BRICK_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "white_concrete_slab"), new BlockItem(Blocks.WHITE_CONCRETE_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "orange_concrete_slab"), new BlockItem(Blocks.ORANGE_CONCRETE_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "magenta_concrete_slab"), new BlockItem(Blocks.MAGENTA_CONCRETE_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "light_blue_concrete_slab"), new BlockItem(Blocks.LIGHT_BLUE_CONCRETE_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "yellow_concrete_slab"), new BlockItem(Blocks.YELLOW_CONCRETE_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "lime_concrete_slab"), new BlockItem(Blocks.LIME_CONCRETE_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "pink_concrete_slab"), new BlockItem(Blocks.PINK_CONCRETE_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "gray_concrete_slab"), new BlockItem(Blocks.GRAY_CONCRETE_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "light_gray_concrete_slab"), new BlockItem(Blocks.LIGHT_GRAY_CONCRETE_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "cyan_concrete_slab"), new BlockItem(Blocks.CYAN_CONCRETE_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "purple_concrete_slab"), new BlockItem(Blocks.PURPLE_CONCRETE_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "blue_concrete_slab"), new BlockItem(Blocks.BLUE_CONCRETE_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "brown_concrete_slab"), new BlockItem(Blocks.BROWN_CONCRETE_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "green_concrete_slab"), new BlockItem(Blocks.GREEN_CONCRETE_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "red_concrete_slab"), new BlockItem(Blocks.RED_CONCRETE_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "black_concrete_slab"), new BlockItem(Blocks.BLACK_CONCRETE_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "white_concrete_stairs"), new BlockItem(Blocks.WHITE_CONCRETE_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "orange_concrete_stairs"), new BlockItem(Blocks.ORANGE_CONCRETE_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "magenta_concrete_stairs"), new BlockItem(Blocks.MAGENTA_CONCRETE_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "light_blue_concrete_stairs"), new BlockItem(Blocks.LIGHT_BLUE_CONCRETE_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "yellow_concrete_stairs"), new BlockItem(Blocks.YELLOW_CONCRETE_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "lime_concrete_stairs"), new BlockItem(Blocks.LIME_CONCRETE_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "pink_concrete_stairs"), new BlockItem(Blocks.PINK_CONCRETE_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "gray_concrete_stairs"), new BlockItem(Blocks.GRAY_CONCRETE_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "light_gray_concrete_stairs"), new BlockItem(Blocks.LIGHT_GRAY_CONCRETE_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "cyan_concrete_stairs"), new BlockItem(Blocks.CYAN_CONCRETE_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "purple_concrete_stairs"), new BlockItem(Blocks.PURPLE_CONCRETE_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "blue_concrete_stairs"), new BlockItem(Blocks.BLUE_CONCRETE_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "brown_concrete_stairs"), new BlockItem(Blocks.BROWN_CONCRETE_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "green_concrete_stairs"), new BlockItem(Blocks.GREEN_CONCRETE_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "red_concrete_stairs"), new BlockItem(Blocks.RED_CONCRETE_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "black_concrete_stairs"), new BlockItem(Blocks.BLACK_CONCRETE_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "white_terracotta_slab"), new BlockItem(Blocks.WHITE_TERRACOTTA_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "orange_terracotta_slab"), new BlockItem(Blocks.ORANGE_TERRACOTTA_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "magenta_terracotta_slab"), new BlockItem(Blocks.MAGENTA_TERRACOTTA_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "light_blue_terracotta_slab"), new BlockItem(Blocks.LIGHT_BLUE_TERRACOTTA_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "yellow_terracotta_slab"), new BlockItem(Blocks.YELLOW_TERRACOTTA_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "lime_terracotta_slab"), new BlockItem(Blocks.LIME_TERRACOTTA_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "pink_terracotta_slab"), new BlockItem(Blocks.PINK_TERRACOTTA_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "gray_terracotta_slab"), new BlockItem(Blocks.GRAY_TERRACOTTA_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "light_gray_terracotta_slab"), new BlockItem(Blocks.LIGHT_GRAY_TERRACOTTA_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "cyan_terracotta_slab"), new BlockItem(Blocks.CYAN_TERRACOTTA_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "purple_terracotta_slab"), new BlockItem(Blocks.PURPLE_TERRACOTTA_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "blue_terracotta_slab"), new BlockItem(Blocks.BLUE_TERRACOTTA_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "brown_terracotta_slab"), new BlockItem(Blocks.BROWN_TERRACOTTA_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "green_terracotta_slab"), new BlockItem(Blocks.GREEN_TERRACOTTA_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "red_terracotta_slab"), new BlockItem(Blocks.RED_TERRACOTTA_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "black_terracotta_slab"), new BlockItem(Blocks.BLACK_TERRACOTTA_SLAB_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "white_terracotta_stairs"), new BlockItem(Blocks.WHITE_TERRACOTTA_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "orange_terracotta_stairs"), new BlockItem(Blocks.ORANGE_TERRACOTTA_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "magenta_terracotta_stairs"), new BlockItem(Blocks.MAGENTA_TERRACOTTA_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "light_blue_terracotta_stairs"), new BlockItem(Blocks.LIGHT_BLUE_TERRACOTTA_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "yellow_terracotta_stairs"), new BlockItem(Blocks.YELLOW_TERRACOTTA_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "lime_terracotta_stairs"), new BlockItem(Blocks.LIME_TERRACOTTA_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "pink_terracotta_stairs"), new BlockItem(Blocks.PINK_TERRACOTTA_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "gray_terracotta_stairs"), new BlockItem(Blocks.GRAY_TERRACOTTA_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "light_gray_terracotta_stairs"), new BlockItem(Blocks.LIGHT_GRAY_TERRACOTTA_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "cyan_terracotta_stairs"), new BlockItem(Blocks.CYAN_TERRACOTTA_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "purple_terracotta_stairs"), new BlockItem(Blocks.PURPLE_TERRACOTTA_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "blue_terracotta_stairs"), new BlockItem(Blocks.BLUE_TERRACOTTA_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "brown_terracotta_stairs"), new BlockItem(Blocks.BROWN_TERRACOTTA_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "green_terracotta_stairs"), new BlockItem(Blocks.GREEN_TERRACOTTA_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "red_terracotta_stairs"), new BlockItem(Blocks.RED_TERRACOTTA_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(Mosk.MOD_ID, "black_terracotta_stairs"), new BlockItem(Blocks.BLACK_TERRACOTTA_STAIRS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

    }
}
