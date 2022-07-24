package com.mango.anosk.mosk.items;

import com.mango.anosk.mosk.blocks.Blocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Items {
    public static final RopeItem ROPE_ITEM = new RopeItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(16));

    public static void initItems() {
        Registry.register(Registry.ITEM, new Identifier("mosk", "rope_item"), ROPE_ITEM);
        Registry.register(Registry.ITEM, new Identifier("mosk", "hupper_block"), new BlockItem(Blocks.HUPPER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));
        Registry.register(Registry.ITEM, new Identifier("mosk", "mushroom_parasite_block"), new BlockItem(Blocks.MUSHROOM_PARASITE_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.ITEM, new Identifier("mosk", "pulley_block"), new BlockItem(Blocks.PULLEY_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));
    }
}
