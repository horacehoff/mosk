package com.mango.anosk.mosk.blocks.entity;

import com.mango.anosk.mosk.blocks.HupperBlock;
import com.mango.anosk.mosk.blocks.interfaces.Hupper;
import com.mango.anosk.mosk.gui.HupperScreenHandler;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.IntStream;

public class HupperBlockEntity extends LootableContainerBlockEntity implements Hupper {

    private DefaultedList<ItemStack> inventory;
    private int transferCooldown;
    private long lastTickTime;

    public HupperBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntities.HUPPER_BLOCK_ENTITY, pos, state);
        this.inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);
        this.transferCooldown = -1;
    }

    /**
     * If the loot table is not deserialized, then read the inventory from the NBT
     *
     * @param nbt The NbtCompound that is being read from.
     */
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.deserializeLootTable(nbt)) {
            Inventories.readNbt(nbt, this.inventory);
        }

        this.transferCooldown = nbt.getInt("TransferCooldown");
    }

    /**
     * If the loot table is not serialized, then the inventory is serialized
     *
     * @param nbt The NBT tag to write to.
     */
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (!this.serializeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.inventory);
        }

        nbt.putInt("TransferCooldown", this.transferCooldown);
    }

    public int size() {
        return this.inventory.size();
    }

    /**
     * Remove an ItemStack at a given slot in this inventory.
     *
     * @param slot The slot number to remove the item from.
     * @param amount The amount of items to remove from the slot.
     * @return The itemstack that was removed from the inventory.
     */
    public ItemStack removeStack(int slot, int amount) {
        this.checkLootInteraction((PlayerEntity)null);
        return Inventories.splitStack(this.getInvStackList(), slot, amount);
    }


    /**
     * Set an ItemStack at a given slot in this inventory.
     *
     * @param slot The slot number to set the stack in.
     * @param stack The ItemStack to set the slot to.
     */
    public void setStack(int slot, ItemStack stack) {
        this.checkLootInteraction((PlayerEntity)null);
        this.getInvStackList().set(slot, stack);
        if (stack.getCount() > this.getMaxCountPerStack()) {
            stack.setCount(this.getMaxCountPerStack());
        }
    }

    protected Text getContainerName() {
        return Text.translatable("container.hupper");
    }

    /**
     * If the block entity is not on cooldown, then it will attempt to extract items from the world and insert them into
     * the block entity's inventory
     *
     * @param world The world the block is in
     * @param pos The position of the block
     * @param state The current state of the block
     * @param blockEntity The block entity that is being ticked.
     */
    public static void serverTick(World world, BlockPos pos, BlockState state, HupperBlockEntity blockEntity) {
        --blockEntity.transferCooldown;
        blockEntity.lastTickTime = world.getTime();
        if (!blockEntity.needsCooldown()) {
            blockEntity.setTransferCooldown(0);
            insertAndExtract(world, pos, state, blockEntity, () -> {
                return extract((World)world, (Hupper)blockEntity);
            });
        }
    }

    /**
     * If the block entity is not full and the boolean supplier returns true, then the block entity is marked dirty and the
     * function returns true
     *
     * @param world The world the block is in
     * @param pos The position of the block
     * @param state The current state of the block
     * @param blockEntity The block entity of the block.
     * @param booleanSupplier This is a function that returns a boolean. It's used to determine if the block should extract
     * items.
     * @return A boolean value.
     */
    private static boolean insertAndExtract(World world, BlockPos pos, BlockState state, HupperBlockEntity blockEntity, BooleanSupplier booleanSupplier) {
        if (world.isClient) {
            return false;
        } else {
            if (!blockEntity.needsCooldown() && (Boolean)state.get(HupperBlock.ENABLED)) {
                boolean bl = false;
                if (!blockEntity.isEmpty()) {
                    bl = insert(world, pos, state, blockEntity);
                }

                if (!blockEntity.isFull()) {
                    bl |= booleanSupplier.getAsBoolean();
                }

                if (bl) {
                    blockEntity.setTransferCooldown(8);
                    markDirty(world, pos, state);
                    return true;
                }
            }

            return false;
        }
    }

    /**
     * If all the items in the inventory are full, return true, otherwise return false.
     *
     * @return A boolean value.
     */
    private boolean isFull() {
        Iterator var1 = this.inventory.iterator();

        ItemStack itemStack;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            itemStack = (ItemStack)var1.next();
        } while(!itemStack.isEmpty() && itemStack.getCount() == itemStack.getMaxCount());

        return false;
    }

    /**
     * If the inventory is not full, then for each item in the inventory, try to transfer it to the output inventory
     *
     * @param world The world the block is in
     * @param pos The position of the block
     * @param state The blockstate of the block that is being interacted with.
     * @param inventory The inventory that the item is being inserted from.
     * @return A boolean value.
     */
    private static boolean insert(World world, BlockPos pos, BlockState state, Inventory inventory) {
        Inventory inventory2 = getOutputInventory(world, pos, state);
        if (inventory2 == null) {
            return false;
        } else {
            Direction direction = ((Direction)state.get(HupperBlock.FACING)).getOpposite();
            if (isInventoryFull(inventory2, direction)) {
                return false;
            } else {
                for(int i = 0; i < inventory.size(); ++i) {
                    if (!inventory.getStack(i).isEmpty()) {
                        ItemStack itemStack = inventory.getStack(i).copy();
                        ItemStack itemStack2 = transfer(inventory, inventory2, inventory.removeStack(i, 1), direction);
                        if (itemStack2.isEmpty()) {
                            inventory2.markDirty();
                            return true;
                        }

                        inventory.setStack(i, itemStack);
                    }
                }

                return false;
            }
        }
    }

    /**
     * If the inventory is sided, return the available slots for the given side, otherwise return all slots.
     *
     * @param inventory The inventory to check
     * @param side The side of the inventory to check.
     * @return An IntStream of the available slots in the inventory.
     */
    private static IntStream getAvailableSlots(Inventory inventory, Direction side) {
        return inventory instanceof SidedInventory ? IntStream.of(((SidedInventory)inventory).getAvailableSlots(side)) : IntStream.range(0, inventory.size());
    }

    /**
     * "Returns true if all slots in the inventory are full."
     *
     * The function is a bit more complicated than that, but that's the gist of it
     *
     * @param inventory The inventory to check
     * @param direction The direction to check for available slots.
     * @return A boolean value.
     */
    private static boolean isInventoryFull(Inventory inventory, Direction direction) {
        return getAvailableSlots(inventory, direction).allMatch((slot) -> {
            ItemStack itemStack = inventory.getStack(slot);
            return itemStack.getCount() >= itemStack.getMaxCount();
        });
    }

    /**
     * "Returns true if all the slots in the inventory are empty."
     *
     * The first thing we do is get a list of all the slots in the inventory that are facing the given direction. We do
     * this by calling the `getAvailableSlots` function we defined earlier
     *
     * @param inv The inventory to check
     * @param facing The direction the inventory is facing.
     * @return A boolean value.
     */
    private static boolean isInventoryEmpty(Inventory inv, Direction facing) {
        return getAvailableSlots(inv, facing).allMatch((slot) -> {
            return inv.getStack(slot).isEmpty();
        });
    }

    /**
     * If the input inventory is not null, and it is not empty, then extract from it
     *
     * @param world The world the hupper is in
     * @param hupper The hupper block
     * @return A boolean
     */
    public static boolean extract(World world, Hupper hupper) {
        Inventory inventory = getInputInventory(world, hupper);
        if (inventory != null) {
            Direction direction = Direction.DOWN;
            return isInventoryEmpty(inventory, direction) ? false : getAvailableSlots(inventory, direction).anyMatch((slot) -> {
                return extract(hupper, inventory, slot, direction);
            });
        } else {
            return false;
        }
    }

    /**
     * If the item in the slot can be extracted, then remove it from the inventory and add it to the hupper
     *
     * @param hupper The HopperBlockEntity that is calling this method.
     * @param inventory The inventory that the item is being extracted from.
     * @param slot The slot number of the item you want to extract.
     * @param side The side of the block that the item is being extracted from.
     * @return A boolean value.
     */
    private static boolean extract(Hupper hupper, Inventory inventory, int slot, Direction side) {
        ItemStack itemStack = inventory.getStack(slot);
        if (!itemStack.isEmpty() && canExtract(inventory, itemStack, slot, side)) {
            ItemStack itemStack2 = itemStack.copy();
            ItemStack itemStack3 = transfer(inventory, hupper, inventory.removeStack(slot, 1), (Direction)null);
            if (itemStack3.isEmpty()) {
                inventory.markDirty();
                return true;
            }

            inventory.setStack(slot, itemStack2);
        }

        return false;
    }

    /**
     * If the item entity's stack can be transferred to the inventory, then the item entity is discarded.
     *
     * @param inventory The inventory to extract the item from
     * @param itemEntity The item entity to extract from
     * @return A boolean value
     */
    public static boolean extract(Inventory inventory, ItemEntity itemEntity) {
        boolean bl = false;
        ItemStack itemStack = itemEntity.getStack().copy();
        ItemStack itemStack2 = transfer((Inventory)null, inventory, itemStack, (Direction)null);
        if (itemStack2.isEmpty()) {
            bl = true;
            itemEntity.discard();
        } else {
            itemEntity.setStack(itemStack2);
        }

        return bl;
    }

    /**
     * If the inventory is sided, transfer the item to the slots on the side, otherwise transfer the item to all slots.
     *
     * @param from The inventory to take the item from.
     * @param to The inventory to transfer the item to
     * @param stack The itemstack to transfer
     * @param side The side of the inventory to transfer to.
     * @return The remaining stack
     */
    public static ItemStack transfer(@org.jetbrains.annotations.Nullable Inventory from, Inventory to, ItemStack stack, @org.jetbrains.annotations.Nullable Direction side) {
        if (to instanceof SidedInventory && side != null) {
            SidedInventory sidedInventory = (SidedInventory)to;
            int[] is = sidedInventory.getAvailableSlots(side);

            for(int i = 0; i < is.length && !stack.isEmpty(); ++i) {
                stack = transfer(from, to, stack, is[i], side);
            }
        } else {
            int j = to.size();

            for(int k = 0; k < j && !stack.isEmpty(); ++k) {
                stack = transfer(from, to, stack, k, side);
            }
        }

        return stack;
    }

    /**
     * If the inventory is not a sided inventory, then return true. Otherwise, return the result of the sided inventory's
     * canInsert function
     *
     * @param inventory The inventory to insert into
     * @param stack The itemstack that is being inserted
     * @param slot The slot to insert the item into
     * @param side The side of the block that the inventory is on.
     * @return A boolean value
     */
    private static boolean canInsert(Inventory inventory, ItemStack stack, int slot, @org.jetbrains.annotations.Nullable Direction side) {
        if (!inventory.isValid(slot, stack)) {
            return false;
        } else {
            return !(inventory instanceof SidedInventory) || ((SidedInventory)inventory).canInsert(slot, stack, side);
        }
    }

    /**
     * If the inventory is not sided, return true. Otherwise, return the result of the sided inventory's canExtract
     * function.
     *
     * @param inv The inventory that the item is being extracted from.
     * @param stack The itemstack that is being extracted
     * @param slot The slot number of the itemstack in the inventory
     * @param facing The direction the item is being extracted from.
     * @return A boolean value.
     */
    private static boolean canExtract(Inventory inv, ItemStack stack, int slot, Direction facing) {
        return !(inv instanceof SidedInventory) || ((SidedInventory)inv).canExtract(slot, stack, facing);
    }

    /**
     * If the item can be inserted into the inventory, then it will be inserted
     *
     * @param from The inventory that the item is being taken from.
     * @param to The inventory to transfer to
     * @param stack The itemstack that is being transferred
     * @param slot The slot in the inventory to transfer to
     * @param side The side of the block that the item is being inserted into.
     * @return The itemstack that is being transferred.
     */
    private static ItemStack transfer(@org.jetbrains.annotations.Nullable Inventory from, Inventory to, ItemStack stack, int slot, @org.jetbrains.annotations.Nullable Direction side) {
        ItemStack itemStack = to.getStack(slot);
        if (canInsert(to, stack, slot, side)) {
            boolean bl = false;
            boolean bl2 = to.isEmpty();
            if (itemStack.isEmpty()) {
                to.setStack(slot, stack);
                stack = ItemStack.EMPTY;
                bl = true;
            } else if (canMergeItems(itemStack, stack)) {
                int i = stack.getMaxCount() - itemStack.getCount();
                int j = Math.min(stack.getCount(), i);
                stack.decrement(j);
                itemStack.increment(j);
                bl = j > 0;
            }

            if (bl) {
                if (bl2 && to instanceof HupperBlockEntity) {
                    HupperBlockEntity hupperBlockEntity = (HupperBlockEntity)to;
                    if (!hupperBlockEntity.isDisabled()) {
                        int j = 0;
                        if (from instanceof HupperBlockEntity) {
                            HupperBlockEntity hupperBlockEntity2 = (HupperBlockEntity)from;
                            if (hupperBlockEntity.lastTickTime >= hupperBlockEntity2.lastTickTime) {
                                j = 1;
                            }
                        }

                        hupperBlockEntity.setTransferCooldown(8 - j);
                    }
                }

                to.markDirty();
            }
        }

        return stack;
    }

    /**
     * If the block is facing up or down, return the inventory above or below it. Otherwise, return the inventory in front
     * of it
     *
     * @param world The world the block is in
     * @param pos The position of the block
     * @param state The block state of the block that is being interacted with.
     * @return The inventory of the block that is being hupped to.
     */
    @org.jetbrains.annotations.Nullable
    private static Inventory getOutputInventory(World world, BlockPos pos, BlockState state) {
        Direction direction = (Direction)state.get(HupperBlock.FACING);
        if (direction == Direction.DOWN || direction == direction.UP) {
            return getInventoryAt(world, pos.offset(direction.UP));
        } else {
            return getInventoryAt(world, pos.offset(direction));
        }
    }

    @org.jetbrains.annotations.Nullable
    private static Inventory getInputInventory(World world, Hupper hupper) {
        return getInventoryAt(world, hupper.getHupperX(), hupper.getHupperY() - 1.0D, hupper.getHupperZ());
    }


    @org.jetbrains.annotations.Nullable
    public static Inventory getInventoryAt(World world, BlockPos pos) {
        return getInventoryAt(world, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D);
    }

    /**
     * If there's an inventory at the given coordinates, return it. Otherwise, return null.
     *
     * @param world The world the block is in
     * @param x The x coordinate of the block
     * @param y The y coordinate of the block to check
     * @param z The z coordinate of the block to check.
     * @return A list of entities that have inventories.
     */
    @Nullable
    private static Inventory getInventoryAt(World world, double x, double y, double z) {
        Inventory inventory = null;
        BlockPos blockPos = new BlockPos(x, y, z);
        BlockState blockState = world.getBlockState(blockPos);
        Block block = blockState.getBlock();
        if (block instanceof InventoryProvider) {
            inventory = ((InventoryProvider)block).getInventory(blockState, world, blockPos);
        } else if (blockState.hasBlockEntity()) {
            BlockEntity blockEntity = world.getBlockEntity(blockPos);
            if (blockEntity instanceof Inventory) {
                inventory = (Inventory)blockEntity;
                if (inventory instanceof ChestBlockEntity && block instanceof ChestBlock) {
                    inventory = ChestBlock.getInventory((ChestBlock)block, blockState, world, blockPos, true);
                }
            }
        }

        if (inventory == null) {
            List<Entity> list = world.getOtherEntities((Entity)null, new Box(x - 0.5D, y - 0.5D, z - 0.5D, x + 0.5D, y + 0.5D, z + 0.5D), EntityPredicates.VALID_INVENTORIES);
            if (!list.isEmpty()) {
                inventory = (Inventory)list.get(world.random.nextInt(list.size()));
            }
        }

        return (Inventory)inventory;
    }

    /**
     * If the two items are the same, have the same damage value, and have the same NBT data, then they can be merged.
     *
     * @param first The first itemstack to be merged
     * @param second The itemstack that is being merged into the first itemstack.
     * @return A boolean value.
     */
    private static boolean canMergeItems(ItemStack first, ItemStack second) {
        if (!first.isOf(second.getItem())) {
            return false;
        } else if (first.getDamage() != second.getDamage()) {
            return false;
        } else if (first.getCount() > first.getMaxCount()) {
            return false;
        } else {
            return ItemStack.areNbtEqual(first, second);
        }
    }

    public double getHupperX() {
        return (double)this.pos.getX() + 0.5D;
    }

    public double getHupperY() {
        return (double)this.pos.getY() + 0.5D;
    }

    public double getHupperZ() {
        return (double)this.pos.getZ() + 0.5D;
    }

    private void setTransferCooldown(int transferCooldown) {
        this.transferCooldown = transferCooldown;
    }

    private boolean needsCooldown() {
        return this.transferCooldown > 0;
    }

    private boolean isDisabled() {
        return this.transferCooldown > 8;
    }

    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inventory;
    }

    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inventory = list;
    }



    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new HupperScreenHandler(syncId, playerInventory, this);
    }
}


