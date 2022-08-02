package com.mango.anosk.mosk.blocks;

import net.minecraft.block.Blocks;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WickBlock extends HorizontalFacingBlock {

    // Declare the two properties of this block
    private static IntProperty status = IntProperty.of("status", 1, 3);
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public WickBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH).with(FACING, Direction.NORTH).with(status, 1)));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return createCuboidShape(0, 0, 0, 16f, 1f, 16);
    }

    // WIP -> Spawn a smoke particle when a Wick becomes lit
    public static void spawnSmokeParticle(World world, BlockPos pos, boolean isSignal, boolean lotsOfSmoke) {
        Random random = world.getRandom();
        if (lotsOfSmoke) {
            world.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + 0.5 + random.nextDouble() / 4.0 * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + 0.4, (double)pos.getZ() + 0.5 + random.nextDouble() / 4.0 * (double)(random.nextBoolean() ? 1 : -1), 0.0, 0.005, 0.0);
        }
    }


    /**
     * If the player right clicks the block with a flint and steel, the block will change to the next state in the state
     * manager
     *
     * @param state The current blockstate of the block
     * @param world The world the block is in
     * @param pos The position of the block
     * @param player The player who interacted with the block
     * @param hand The hand the player is using to interact with the block.
     * @param hit The BlockHitResult object that contains information about the block that was hit.
     * @return ActionResult.SUCCESS
     */
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getStackInHand(hand).getItem() == Items.FLINT_AND_STEEL) {
                if (world.getBlockState(pos).toString().contains("north")) {
                    world.setBlockState(pos, this.stateManager.getStates().get(1));
                } else if (world.getBlockState(pos).toString().contains("south")) {
                    world.setBlockState(pos, this.stateManager.getStates().get(4));
                } else if (world.getBlockState(pos).toString().contains("west")) {
                    world.setBlockState(pos, this.stateManager.getStates().get(7));
                } else if (world.getBlockState(pos).toString().contains("east")) {
                    world.setBlockState(pos, this.stateManager.getStates().get(10));
                }
                spawnSmokeParticle(world, pos, false, true);

        }
        return ActionResult.SUCCESS;
    }

    /**
     * When a block is placed next to a wick block or updated, it will check if the wick block is lit, and if it is, it will set the
     * block to the lit version of itself, and if there is a TNT block next to it, it will light the TNT block
     *
     * @param state The current block state
     * @param world The world the block is in
     * @param pos The position of the block
     * @param sourceBlock The block that is being updated
     * @param sourcePos The position of the block that was placed
     * @param notify Whether or not to notify the block of the update.
     */
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (sourceBlock instanceof WickBlock && world.getBlockState(sourcePos).toString().contains("2")) {
            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            scheduledExecutorService.schedule(() -> {
                if (world.getBlockState(pos).toString().contains("north")) {
                    world.setBlockState(pos, this.stateManager.getStates().get(1));
                } else if (world.getBlockState(pos).toString().contains("south")) {
                    world.setBlockState(pos, this.stateManager.getStates().get(4));
                } else if (world.getBlockState(pos).toString().contains("west")) {
                    world.setBlockState(pos, this.stateManager.getStates().get(7));
                } else if (world.getBlockState(pos).toString().contains("east")) {
                    world.setBlockState(pos, this.stateManager.getStates().get(10));
                }
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
        stateManager.add(FACING,status);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }
}
