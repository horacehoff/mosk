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

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ctx) {
        Direction dir = state.get(FACING);
        return switch (dir) {
/*            case NORTH:
                return VoxelShapes.union(
                        createCuboidShape(-1, 12, 25, 1, 15, 26),//1
                        createCuboidShape(-3, 12, 22, -2, 15, 24),
                        createCuboidShape(-2, 12, 24, -1, 15, 25),
                        createCuboidShape(10, 12, 22, 11, 15, 24),
                        createCuboidShape(9, 12, 24, 10, 15, 25),
                        createCuboidShape(7, 12, 25, 9, 15, 26),
                        createCuboidShape(-1, 12, 12, 1, 15, 13),
                        createCuboidShape(7, 12, 12, 9, 15, 13),
                        createCuboidShape(10, 12, 14, 11, 15, 16),
                        createCuboidShape(9, 12, 13, 10, 15, 14),//10
                        createCuboidShape(-3, 12, 14, -2, 15, 16),
                        createCuboidShape(-2, 12, 13, -1, 15, 14),
                        createCuboidShape(1, 12, 11, 7, 15, 12),
                        createCuboidShape(-4, 12, 16, -3, 15, 22),
                        createCuboidShape(1, 12, 26, 7, 15, 27),
                        createCuboidShape(-1, 12, 14, 9, 15, 24),
                        createCuboidShape(-2, 12, 14, -1, 15, 24),
                        createCuboidShape(-3, 12, 16, -2, 15, 22),
                        createCuboidShape(1, 12, 25, 7, 15, 26),
                        createCuboidShape(-1, 12, 24, 9, 15, 25),//20
                        createCuboidShape(10, 12, 16, 11, 15, 22),
                        createCuboidShape(9, 12, 14, 10, 15, 24),
                        createCuboidShape(1, 12, 12, 7, 15, 13),
                        createCuboidShape(-1, 12, 13, 9, 15, 14)//24
                        );*/
            case NORTH -> VoxelShapes.union(createCuboidShape(-8, 0, 0, 24, 13, 16));
            case SOUTH -> VoxelShapes.union(createCuboidShape(-8, 0, 0, 24, 13, 16));
            case EAST -> VoxelShapes.union(createCuboidShape(0, 0, -8, 16, 13, 24));
            case WEST -> VoxelShapes.union(createCuboidShape(0, 0, -8, 16, 13, 24));
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
