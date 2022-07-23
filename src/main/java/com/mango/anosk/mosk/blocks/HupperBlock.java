package com.mango.anosk.mosk.blocks;

import com.mango.anosk.mosk.blocks.entity.BlockEntities;
import com.mango.anosk.mosk.blocks.entity.HupperBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.stat.Stats;
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

import java.util.stream.IntStream;

import static net.minecraft.block.entity.HopperBlockEntity.getInventoryAt;


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

    public static void transferItems(World world, BlockPos blockPos, BlockState state) {
        switch (state.get(FACING)) {
            case UP:
            case DOWN:
                Inventory down_inventory = getInventoryAt(world, blockPos.add(0, -1, 0));
                Inventory current_inventory = getInventoryAt(world, blockPos);
                Inventory upper_inventory = getInventoryAt(world, blockPos.add(0, 1, 0));
                if (upper_inventory != null && down_inventory != null && current_inventory != null && down_inventory.isEmpty() != Boolean.TRUE) {
                    int to_remove = 0;
                    for (int t = 0; t < down_inventory.size(); t++) {
                        if (down_inventory.getStack(t) != ItemStack.EMPTY) {
                            to_remove = t;
                            break;
                        }
                    }
                    for (int i = 0; i < upper_inventory.size(); i++) {
                        if (upper_inventory.getStack(i) == ItemStack.EMPTY) {
                            upper_inventory.setStack(i, down_inventory.getStack(to_remove));
                            down_inventory.removeStack(to_remove);
                            break;
                        }
                    }
                } else if (upper_inventory != null && down_inventory != null && current_inventory != null && current_inventory.isEmpty() != Boolean.TRUE) {
                    int to_remove = 0;
                    for (int t = 0; t < 54; t++) {
                        if (current_inventory.getStack(t) != ItemStack.EMPTY) {
                            to_remove = t;
                            break;
                        }
                    }
                    for (int i = 0; i < 54; i++) {
                        if (upper_inventory.getStack(i) == ItemStack.EMPTY) {
                            upper_inventory.setStack(i, current_inventory.getStack(to_remove));
                            current_inventory.removeStack(to_remove);
                            break;
                        }
                    }
                }
            case NORTH:
                down_inventory = getInventoryAt(world, blockPos.add(0, -1, 0));
                current_inventory = getInventoryAt(world, blockPos);
                upper_inventory = getInventoryAt(world, blockPos.add(0, 0, -1));
                if (upper_inventory != null && down_inventory != null && current_inventory != null && down_inventory.isEmpty() != Boolean.TRUE) {
                    int to_remove = 0;
                    for (int t = 0; t < 54; t++) {
                        if (down_inventory.getStack(t) != ItemStack.EMPTY) {
                            to_remove = t;
                            break;
                        }
                    }
                    for (int i = 0; i < 54; i++) {
                        if (upper_inventory.getStack(i) == ItemStack.EMPTY) {
                            upper_inventory.setStack(i, down_inventory.getStack(to_remove));
                            down_inventory.removeStack(to_remove);
                            break;
                        }
                    }
                } else if (upper_inventory != null && down_inventory != null && current_inventory != null && current_inventory.isEmpty() != Boolean.TRUE) {
                    int to_remove = 0;
                    for (int t = 0; t < 54; t++) {
                        if (current_inventory.getStack(t) != ItemStack.EMPTY) {
                            to_remove = t;
                            break;
                        }
                    }
                    for (int i = 0; i < 54; i++) {
                        if (upper_inventory.getStack(i) == ItemStack.EMPTY) {
                            upper_inventory.setStack(i, current_inventory.getStack(to_remove));
                            current_inventory.removeStack(to_remove);
                            break;
                        }
                    }
                }
            case SOUTH:
                down_inventory = getInventoryAt(world, blockPos.add(0, -1, 0));
                current_inventory = getInventoryAt(world, blockPos);
                upper_inventory = getInventoryAt(world, blockPos.add(0, 0, 1));
                if (upper_inventory != null && down_inventory != null && current_inventory != null && down_inventory.isEmpty() != Boolean.TRUE) {
                    int to_remove = 0;
                    for (int t = 0; t < 54; t++) {
                        if (down_inventory.getStack(t) != ItemStack.EMPTY) {
                            to_remove = t;
                            break;
                        }
                    }
                    for (int i = 0; i < 54; i++) {
                        if (upper_inventory.getStack(i) == ItemStack.EMPTY) {
                            upper_inventory.setStack(i, down_inventory.getStack(to_remove));
                            down_inventory.removeStack(to_remove);
                            break;
                        }
                    }
                } else if (upper_inventory != null && down_inventory != null && current_inventory != null && current_inventory.isEmpty() != Boolean.TRUE) {
                    int to_remove = 0;
                    for (int t = 0; t < 54; t++) {
                        if (current_inventory.getStack(t) != ItemStack.EMPTY) {
                            to_remove = t;
                            break;
                        }
                    }
                    for (int i = 0; i < 54; i++) {
                        if (upper_inventory.getStack(i) == ItemStack.EMPTY) {
                            upper_inventory.setStack(i, current_inventory.getStack(to_remove));
                            current_inventory.removeStack(to_remove);
                            break;
                        }
                    }
                }
            case EAST:
                down_inventory = getInventoryAt(world, blockPos.add(0, -1, 0));
                current_inventory = getInventoryAt(world, blockPos);
                upper_inventory = getInventoryAt(world, blockPos.add(1, 0, 0));
                if (upper_inventory != null && down_inventory != null && current_inventory != null && down_inventory.isEmpty() != Boolean.TRUE) {
                    int to_remove = 0;
                    for (int t = 0; t < down_inventory.size(); t++) {
                        if (down_inventory.getStack(t) != ItemStack.EMPTY) {
                            to_remove = t;
                            break;
                        }
                    }
                    for (int i = 0; i < upper_inventory.size(); i++) {
                        if (upper_inventory.getStack(i) == ItemStack.EMPTY) {
                            upper_inventory.setStack(i, down_inventory.getStack(to_remove));
                            down_inventory.removeStack(to_remove);
                            break;
                        }
                    }
                } else if (upper_inventory != null && down_inventory != null && current_inventory != null && current_inventory.isEmpty() != Boolean.TRUE) {
                    int to_remove = 0;
                    for (int t = 0; t < 54; t++) {
                        if (current_inventory.getStack(t) != ItemStack.EMPTY) {
                            to_remove = t;
                            break;
                        }
                    }
                    for (int i = 0; i < 54; i++) {
                        if (upper_inventory.getStack(i) == ItemStack.EMPTY) {
                            upper_inventory.setStack(i, current_inventory.getStack(to_remove));
                            current_inventory.removeStack(to_remove);
                            break;
                        }
                    }
                }
            case WEST:
                down_inventory = getInventoryAt(world, blockPos.add(0, -1, 0));
                current_inventory = getInventoryAt(world, blockPos);
                upper_inventory = getInventoryAt(world, blockPos.add(-1, 0, 0));
                if (upper_inventory != null && down_inventory != null && current_inventory != null && down_inventory.isEmpty() != Boolean.TRUE) {
                    int to_remove = 0;
                    for (int t = 0; t < down_inventory.size(); t++) {
                        if (down_inventory.getStack(t) != ItemStack.EMPTY) {
                            to_remove = t;
                            break;
                        }
                    }
                    for (int i = 0; i < upper_inventory.size(); i++) {
                        if (upper_inventory.getStack(i) == ItemStack.EMPTY) {
                            upper_inventory.setStack(i, down_inventory.getStack(to_remove));
                            down_inventory.removeStack(to_remove);
                            break;
                        }
                    }
                } else if (upper_inventory != null && down_inventory != null && current_inventory != null && current_inventory.isEmpty() != Boolean.TRUE) {
                    int to_remove = 0;
                    for (int t = 0; t < 54; t++) {
                        if (current_inventory.getStack(t) != ItemStack.EMPTY) {
                            to_remove = t;
                            break;
                        }
                    }
                    for (int i = 0; i < 54; i++) {
                        if (upper_inventory.getStack(i) == ItemStack.EMPTY) {
                            upper_inventory.setStack(i, current_inventory.getStack(to_remove));
                            current_inventory.removeStack(to_remove);
                            break;
                        }
                    }
                }
        }
    }

    private static IntStream getAvailableSlots(Inventory inventory, Direction side) {
        return inventory instanceof SidedInventory ? IntStream.of(((SidedInventory)inventory).getAvailableSlots(side)) : IntStream.range(0, inventory.size());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
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
                return BOTTOM_SHAPE;
            }
            case WEST: {
                return BOTTOM_SHAPE;
            }
            case EAST: {
                return BOTTOM_SHAPE;
            }
        }
        return DEFAULT_SHAPE;
    }

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

//    @Override
//    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
//        transferItems(world, blockPos, blockState);
//        if (world.isClient) return ActionResult.SUCCESS;
//        Inventory blockEntity = (Inventory) world.getBlockEntity(blockPos);
//        return ActionResult.SUCCESS;
//    }
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
