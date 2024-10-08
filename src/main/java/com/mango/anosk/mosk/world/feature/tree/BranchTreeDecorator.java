package com.mango.anosk.mosk.world.feature.tree;

import com.mango.anosk.mosk.mixin.TreeDecoratorTypeMixin;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BranchTreeDecorator extends BeehiveTreeDecorator {
    public static final Codec<BranchTreeDecorator> CODEC = Codec.unit(() -> new BranchTreeDecorator(0.75f));
    public static final TreeDecoratorType<BranchTreeDecorator> BRANCH = TreeDecoratorTypeMixin.invokeBranchRegister("branch", BranchTreeDecorator.CODEC);
    private final float probability;

    public BranchTreeDecorator(float probability) {
        super(probability);
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return BRANCH;
    }

    /**
     * If the random number generator generates a number less than the probability, then it will generate a birch log at a
     * random position in the tree
     *
     * @param generator The generator object that contains all the information about the tree.
     */

    private BlockPos get_equal_y(ObjectArrayList<BlockPos> logs, int desired_y) {
        for (BlockPos log: logs) {
            if (log.getY() == desired_y) {
                return log;
            }
        }
        return null;
    }


    @Override
    public void generate(TreeDecorator.Generator generator) {

        Random random = generator.getRandom();
        Direction random_direction = Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST).get(random.nextInt(3));
        if (random.nextFloat() >= this.probability) {
            return;
        }
        ObjectArrayList<BlockPos> list = generator.getLeavesPositions();
        ObjectArrayList<BlockPos> list2 = generator.getLogPositions();
        int i = !list.isEmpty() ? Math.max(((BlockPos)list.get(0)).getY() - 1, ((BlockPos)list2.get(0)).getY() + 1) : Math.min(((BlockPos)list2.get(0)).getY() + 1 + random.nextInt(3), ((BlockPos)list2.get(list2.size() - 1)).getY());
        List list3 = list2.stream().filter(pos -> pos.getY() == i).flatMap(pos -> Stream.of((Direction[])Direction.Type.HORIZONTAL.stream().filter(direction -> direction != random_direction.getOpposite()).toArray(Direction[]::new)).map(pos::offset)).collect(Collectors.toList());
        if (list3.isEmpty()) {
            return;
        }
        Collections.shuffle(list3);
        Optional<BlockPos> optional = list3.stream().filter(pos -> generator.isAir((BlockPos)pos) && generator.isAir(((BlockPos) pos).offset(random_direction))).findFirst();
        if (optional.isEmpty()) {
            return;
        }
        BlockPos placement = optional.get().add(0, -1*ThreadLocalRandom.current().nextInt(2, 3 + 1), 0);
        if (get_equal_y(list2, placement.getY()) == null) {
            generator.replace(placement, (BlockState)Blocks.BIRCH_LOG.getStateManager().getStates().get(0));
        } else {
            BlockPos equal_log_y = placement.subtract(get_equal_y(list2, placement.getY()));
            if (Objects.equals(equal_log_y, new BlockPos(1, 0, 0)) || Objects.equals(equal_log_y, new BlockPos(-1, 0, 0))) {
                generator.replace(placement, (BlockState)Blocks.BIRCH_LOG.getStateManager().getStates().get(0));
            } else if (Objects.equals(equal_log_y, new BlockPos(0, 0, 1)) || Objects.equals(equal_log_y, new BlockPos(0, 0, -1))) {
                generator.replace(placement, (BlockState)Blocks.BIRCH_LOG.getStateManager().getStates().get(2));
            } else {
                generator.replace(placement, (BlockState)Blocks.BIRCH_LOG.getStateManager().getStates().get(0));
            }
        }
        double bee_random = Math.random();
        if (bee_random > 0.9) {
            generator.replace(placement.add(0, -1, 0), (BlockState)Blocks.BEE_NEST.getDefaultState().with(BeehiveBlock.FACING, Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST).get(random.nextInt(3))));

            generator.getWorld().getBlockEntity(placement.add(0, -1, 0), BlockEntityType.BEEHIVE).ifPresent(blockEntity -> {
                int k = 2 + random.nextInt(2);
                for (int j = 0; j < k; ++j) {
                    NbtCompound nbtCompound = new NbtCompound();
                    nbtCompound.putString("id", Registry.ENTITY_TYPE.getId(EntityType.BEE).toString());
                    blockEntity.addBee(nbtCompound, random.nextInt(599), false);
                }
            });
        }
    }
}
