package com.mango.anosk.mosk.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class MushroomParasiteBlock extends Block {
    public MushroomParasiteBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context){
        return VoxelShapes.union(
                createCuboidShape(0.0f, 5.0f, 0.0f, 16.0, 6.0f, 16.0f),
                createCuboidShape(14.0f, 0.0f, 0.0f, 16.0, 5.0f, 16.0f),
                createCuboidShape(0.0f, 0.0f, 0.0f, 2.0, 5.0f, 16.0f),
                createCuboidShape(2, 0, 0, 14, 5, 2),
                createCuboidShape(2, 0, 14, 14, 5, 16),
                createCuboidShape(4, 6, 4, 12, 12, 12),
                createCuboidShape(6, 12, 6, 10, 16, 10)
        );
    }
}
