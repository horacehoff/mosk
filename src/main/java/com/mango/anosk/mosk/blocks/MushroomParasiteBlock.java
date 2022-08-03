package com.mango.anosk.mosk.blocks;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import javax.swing.*;

public class MushroomParasiteBlock extends Block {

    private static BooleanProperty is_double = BooleanProperty.of("is_double");
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public MushroomParasiteBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.SOUTH).with(is_double, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(FACING, is_double);
    }

    /**
     * "Return a VoxelShape that is the union of the VoxelShape of the block and a cuboid shape that is 4 blocks wide, 3
     * blocks tall, and 16 blocks long, and is offset by the direction the block is facing."
     *
     * The VoxelShape of the block is the shape of the block itself, and is returned by the superclass. The cuboid shape is
     * the shape of the block's "extension", and is created using the createCuboidShape() method
     *
     * @param state The blockstate of the block
     * @param view The world the block is in
     * @param pos The position of the block
     * @param ctx The context of the shape.
     * @return A VoxelShape.
     */
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ctx) {
        return switch (state.get(FACING)) {
            case NORTH -> VoxelShapes.union(createCuboidShape(5, 12, -12, 21, 15, 4));
            case SOUTH -> VoxelShapes.union(createCuboidShape(-4, 12, 11, 12, 15, 27));
            case EAST -> VoxelShapes.union(createCuboidShape(11, 12, 4, 27, 15, 20));
            case WEST -> VoxelShapes.union(createCuboidShape(-11, 12, -5, 5, 15, 11));
            default -> VoxelShapes.fullCube();
        };
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }
}
