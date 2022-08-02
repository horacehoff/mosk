package com.mango.anosk.mosk.blocks;

import com.mango.anosk.mosk.blocks.entity.BlockEntities;
import com.mango.anosk.mosk.blocks.entity.HupperBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class HupperBlock extends BlockWithEntity {

    public static final DirectionProperty FACING = Properties.FACING;
    public static final BooleanProperty ENABLED = Properties.ENABLED;
    private static final VoxelShape DEFAULT_SHAPE = VoxelShapes.union(
            createCuboidShape(0, 5, 0, 16, 6, 16),
            createCuboidShape(14, 0, 0, 16, 5, 16),
            createCuboidShape(0, 0, 0, 2, 5, 16),
            createCuboidShape(2, 0, 0, 14, 5, 2),
            createCuboidShape(2, 0, 14, 14, 5, 16),
            createCuboidShape(4, 6, 4, 12, 12, 12),
            createCuboidShape(6, 12, 6, 10, 16, 10)
    );
    private static final VoxelShape BOTTOM_SHAPE = VoxelShapes.union(
            createCuboidShape(0, 5, 0, 16, 6, 16),
            createCuboidShape(14, 0, 0, 16, 5, 16),
            createCuboidShape(0, 0, 0, 2, 5, 16),
            createCuboidShape(2, 0, 0, 14, 5, 2),
            createCuboidShape(2, 0, 14, 14, 5, 16),
            createCuboidShape(4, 6, 4, 12, 12, 12)
    );

    public HupperBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(Properties.FACING, Direction.NORTH));
    }

    /**
     * "Return the VoxelShape of the Block based on the Block's direction."
     *
     * The first thing we do is check the Block's direction. We do this by using the `get()` function on the BlockState.
     * This function takes in a Property as a parameter. In this case, we're using the `FACING` Property
     *
     * @param state The BlockState of the block
     * @param world The world the block is in
     * @param pos The position of the block
     * @param context The context of the shape.
     * @return The VoxelShape of the block.
     */
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Get the VoxelShapes based on the Block's direction
        switch (state.get(FACING)) {
            case DOWN: {
                return DEFAULT_SHAPE;
            }
            case UP: {
                return DEFAULT_SHAPE;
            }
            case NORTH: {
                return VoxelShapes.union(
                        BOTTOM_SHAPE,
                        createCuboidShape(6, 8, 0, 10, 12, 4)
                );
            }
            case SOUTH: {
                return VoxelShapes.union(
                        BOTTOM_SHAPE,
                        createCuboidShape(6, 8, 12, 10, 12, 16)
                );
            }
            case WEST: {
                return VoxelShapes.union(
                        BOTTOM_SHAPE,
                        createCuboidShape(0, 8, 6, 4, 12, 10)
                );
            }
            case EAST: {
                return VoxelShapes.union(
                        BOTTOM_SHAPE,
                        createCuboidShape(12, 8, 6, 16, 12, 10)
                );
            }
        }
        return DEFAULT_SHAPE;
    }

    // Function responsible for the Tick function
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : checkType(type, BlockEntities.HUPPER_BLOCK_ENTITY, HupperBlockEntity::serverTick);
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (itemStack.hasCustomName()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof HupperBlockEntity) {
                ((HupperBlockEntity)blockEntity).setCustomName(itemStack.getName());
            }
        }

    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!oldState.isOf(state.getBlock())) {
            this.updateEnabled(world, pos, state);
        }
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new HupperBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, ENABLED);
    }

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof HupperBlockEntity) {
                ItemScatterer.spawn(world, pos, (HupperBlockEntity)blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    /**
     * If the block is receiving redstone power, set the block's state to enabled. If the block is not receiving redstone
     * power, set the block's state to disabled
     *
     * @param world The world the block is in
     * @param pos The position of the block
     * @param state The current state of the block.
     */
    private void updateEnabled(World world, BlockPos pos, BlockState state) {
        boolean bl = world.isReceivingRedstonePower(pos);
        if (bl != (Boolean)state.get(ENABLED)) {
            world.setBlockState(pos, (BlockState)state.with(ENABLED, bl), 4);
        } else if (world.getBlockEntity(pos.add(0, -1, 0)) instanceof HupperBlockEntity) {
            world.setBlockState(pos, (BlockState)state.with(ENABLED, bl), 4);
        } else if (world.getBlockEntity(pos.add(-1, 0, 0)) instanceof HupperBlockEntity) {
            world.setBlockState(pos, (BlockState)state.with(ENABLED, bl), 4);
        } else if (world.getBlockEntity(pos.add(1, 0, 0)) instanceof HupperBlockEntity) {
            world.setBlockState(pos, (BlockState)state.with(ENABLED, bl), 4);
        } else if (world.getBlockEntity(pos.add(0, 0, -1)) instanceof HupperBlockEntity) {
            world.setBlockState(pos, (BlockState)state.with(ENABLED, bl), 4);
        } else if (world.getBlockEntity(pos.add(0, 0, 1)) instanceof HupperBlockEntity) {
            world.setBlockState(pos, (BlockState)state.with(ENABLED, bl), 4);
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        this.updateEnabled(world, pos, state);

    }

    /**
     * If the player right clicks on the block, open the GUI
     *
     * @param state The current state of the block
     * @param world The world the block is in
     * @param pos The position of the block
     * @param player The player who interacted with the block
     * @param hand The hand the player is using to interact with the block.
     * @param hit The hit result of the block.
     * @return ActionResult.SUCCESS
     */
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof HupperBlockEntity) {
                player.openHandledScreen((HupperBlockEntity)blockEntity);
            }
            return ActionResult.CONSUME;
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide().getOpposite();
        return (BlockState)((BlockState)this.getDefaultState().with(FACING, direction.getAxis() == Direction.Axis.Y ? Direction.DOWN : direction)).with(ENABLED, true);
    }
}
