package com.mango.anosk.mosk.blocks;

import net.minecraft.block.Blocks;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WickBlock extends Block {


    private static IntProperty status = IntProperty.of("status", 1, 3);

    public WickBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(status, 1));
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return createCuboidShape(0, 0, 0, 16f, 1f, 16);
    }

    public static void spawnSmokeParticle(World world, BlockPos pos, boolean isSignal, boolean lotsOfSmoke) {
        Random random = world.getRandom();
        if (lotsOfSmoke) {
            world.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + 0.5 + random.nextDouble() / 4.0 * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + 0.4, (double)pos.getZ() + 0.5 + random.nextDouble() / 4.0 * (double)(random.nextBoolean() ? 1 : -1), 0.0, 0.005, 0.0);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getStackInHand(hand).getItem() == Items.FLINT_AND_STEEL) {
                world.setBlockState(pos, this.stateManager.getStates().get(1));
                spawnSmokeParticle(world, pos, false, true);

        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (sourceBlock instanceof WickBlock && world.getBlockState(sourcePos) == this.stateManager.getStates().get(1)) {
            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            scheduledExecutorService.schedule(() -> {
                world.setBlockState(pos, this.stateManager.getStates().get(1));
                // world.getServer().getCommandManager().execute(world.getServer().getCommandSource(), "fill "+sourcePos.getX()+" "+sourcePos.getY()+" "+sourcePos.getZ()+" "+sourcePos.getX()+" "+sourcePos.getY()+" "+sourcePos.getZ()+" minecraft:air");
                if (world.getBlockState(pos.add(1,0, 0)).getBlock() instanceof TntBlock) {
                    ((TntBlock) world.getBlockState(pos.add(1, 0, 0)).getBlock()).primeTnt(world, pos.add(1,0, 0));
                    world.setBlockState(pos.add(1, 0, 0), Blocks.AIR.getDefaultState());
                } else if (world.getBlockState(pos.add(-1,0, 0)).getBlock() instanceof TntBlock) {
                    ((TntBlock) world.getBlockState(pos.add(-1, 0, 0)).getBlock()).primeTnt(world, pos.add(-1,0, 0));
                    world.setBlockState(pos.add(-1, 0, 0), Blocks.AIR.getDefaultState());
                } else if (world.getBlockState(pos.add(0,0, -1)).getBlock() instanceof TntBlock) {
                    ((TntBlock) world.getBlockState(pos.add(0, 0, -1)).getBlock()).primeTnt(world, pos.add(0, 0, -1));
                    world.setBlockState(pos.add(0, 0, -1), Blocks.AIR.getDefaultState());
                } else if (world.getBlockState(pos.add(0,0, 1)).getBlock() instanceof TntBlock) {
                    ((TntBlock) world.getBlockState(pos.add(0, 0, 1)).getBlock()).primeTnt(world, pos.add(0,0, 1));
                    world.setBlockState(pos.add(0, 0, 1), Blocks.AIR.getDefaultState());
                }
        }, 1, TimeUnit.SECONDS);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(status);
    }
}
