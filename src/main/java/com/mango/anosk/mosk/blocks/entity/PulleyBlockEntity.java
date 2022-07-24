package com.mango.anosk.mosk.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;


public class PulleyBlockEntity extends BlockEntity {

    public PulleyBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntities.PULLEY_BLOCK_ENTITY, pos, state);
    }
}