package com.mango.anosk.mosk.world.feature.tree;

import com.mango.anosk.mosk.blocks.Blocks;
import com.mango.anosk.mosk.mixin.TreeDecoratorTypeMixin;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MushroomTreeDecorator extends BeehiveTreeDecorator {
    public static final Codec<MushroomTreeDecorator> CODEC = Codec.unit(() -> new MushroomTreeDecorator(0.55f));
    public static final TreeDecoratorType<MushroomTreeDecorator> BRANCH = TreeDecoratorTypeMixin.invokeMushroomRegister("mushroom", MushroomTreeDecorator.CODEC);
    private final float probability;


    public MushroomTreeDecorator(float probability) {
        super(probability);
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return BRANCH;
    }

    /**
     * If the random number generator generates a number less than the probability, then it will generate a mushroom
     * parasite block at a random position in the tree
     *
     * @param generator The generator object.
     */
    @Override
    public void generate(Generator generator) {
        Random random = generator.getRandom();
        if (random.nextFloat() >= this.probability) {
            return;
        }
        ObjectArrayList<BlockPos> list = generator.getLeavesPositions();
        ObjectArrayList<BlockPos> list2 = generator.getLogPositions();
        int i = !list.isEmpty() ? Math.max(((BlockPos)list.get(0)).getY() - 1, ((BlockPos)list2.get(0)).getY() + 1) : Math.min(((BlockPos)list2.get(0)).getY() + 1 + random.nextInt(3), ((BlockPos)list2.get(list2.size() - 1)).getY());
        List list3 = list2.stream().filter(pos -> pos.getY() == i).flatMap(pos -> Stream.of((Direction[])Direction.Type.HORIZONTAL.stream().filter(direction -> direction != Direction.SOUTH.getOpposite()).toArray(Direction[]::new)).map(pos::offset)).collect(Collectors.toList());
        if (list3.isEmpty()) {
            return;
        }
        Collections.shuffle(list3);
        Optional<BlockPos> optional = list3.stream().filter(pos -> generator.isAir((BlockPos)pos) && generator.isAir(((BlockPos) pos).offset(Direction.SOUTH))).findFirst();
        if (optional.isEmpty()) {
            return;
        }
        generator.replace(optional.get().add(0, -1*ThreadLocalRandom.current().nextInt(1, 3 + 1), 0), (BlockState) Blocks.MUSHROOM_PARASITE_BLOCK.getDefaultState());
    }
}
