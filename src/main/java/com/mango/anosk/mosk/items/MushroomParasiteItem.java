package com.mango.anosk.mosk.items;

import com.mango.anosk.mosk.blocks.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class MushroomParasiteItem extends Item {
    public MushroomParasiteItem(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        world.setBlockState(playerEntity.getBlockPos().add(0, 10, 0), Blocks.MUSHROOM_PARASITE_UP_BLOCK.getDefaultState());
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
