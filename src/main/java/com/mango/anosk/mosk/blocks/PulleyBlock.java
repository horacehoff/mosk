package com.mango.anosk.mosk.blocks;

import com.mango.anosk.mosk.blocks.entity.PulleyBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class PulleyBlock extends HorizontalFacingBlock implements BlockEntityProvider{
    public PulleyBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.SOUTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    /**
     * If the block is facing north or south, return a cuboid shape that is 16 blocks wide, 13 blocks tall, and 16 blocks
     * long. If the block is facing east or west, return a cuboid shape that is 16 blocks wide, 13 blocks tall, and 16
     * blocks long. Otherwise, return a full cube.
     *
     * @param state The blockstate of the block
     * @param view The world the block is in
     * @param pos The position of the block.
     * @param ctx The context of the shape.
     * @return A VoxelShape that is a union of two cuboids.
     */
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ctx) {
        Direction dir = state.get(FACING);
        return switch (dir) {
            case NORTH, SOUTH -> VoxelShapes.union(createCuboidShape(-8, 0, 0, 24, 13, 16));
            case EAST, WEST -> VoxelShapes.union(createCuboidShape(0, 0, -8, 16, 13, 24));
            default -> VoxelShapes.fullCube();
        };
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing());
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PulleyBlockEntity(pos, state);
    }
}
