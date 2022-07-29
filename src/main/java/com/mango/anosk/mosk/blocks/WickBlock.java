package com.mango.anosk.mosk.blocks;

import com.mango.anosk.mosk.Mosk;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.concurrent.TimeUnit;

public class WickBlock extends Block {


    private static IntProperty status = IntProperty.of("status", 1, 3);

    public WickBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(status, 1));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getStackInHand(hand).getItem() == Items.FLINT_AND_STEEL) {
            Mosk.log(String.valueOf(world.getBlockState(pos)));
            world.setBlockState(pos, this.stateManager.getStates().get(1));
            Mosk.log(String.valueOf(world.getBlockState(pos)));
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (sourceBlock instanceof WickBlock && world.getBlockState(sourcePos) == this.stateManager.getStates().get(1)) {
            try {
                TimeUnit.SECONDS.sleep(1);
                Mosk.log("Nearby WickBlock update, updating..");
                world.setBlockState(pos, this.stateManager.getStates().get(1));
                if (world.getBlockState(pos.add(1,0, 0)).getBlock() instanceof TntBlock) {
                    ((TntBlock) world.getBlockState(pos.add(1, 0, 0)).getBlock()).primeTnt(world, pos.add(1,0, 0));
                } else if (world.getBlockState(pos.add(-1,0, 0)).getBlock() instanceof TntBlock) {
                    ((TntBlock) world.getBlockState(pos.add(-1, 0, 0)).getBlock()).primeTnt(world, pos.add(-1,0, 0));
                } else if (world.getBlockState(pos.add(0,0, -1)).getBlock() instanceof TntBlock) {
                    ((TntBlock) world.getBlockState(pos.add(0, 0, -1)).getBlock()).primeTnt(world, pos.add(0, 0, -1));
                } else if (world.getBlockState(pos.add(0,0, 1)).getBlock() instanceof TntBlock) {
                    ((TntBlock) world.getBlockState(pos.add(0, 0, 1)).getBlock()).primeTnt(world, pos.add(0,0, 1));
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(status);
    }
}
