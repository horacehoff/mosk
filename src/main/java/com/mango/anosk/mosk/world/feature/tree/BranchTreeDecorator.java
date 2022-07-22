//package com.mango.anosk.mosk.world.feature.tree;
//
//import com.mojang.serialization.Codec;
//import it.unimi.dsi.fastutil.objects.ObjectArrayList;
//import net.minecraft.block.Blocks;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.Direction;
//import net.minecraft.util.math.random.Random;
//import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
//import net.minecraft.world.gen.treedecorator.TreeDecorator;
//import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class BranchTreeDecorator extends BeehiveTreeDecorator {
//
//
//    private static final Direction[] GENERATE_DIRECTIONS = (Direction[])Direction.Type.HORIZONTAL.stream().filter(direction -> direction != Direction.SOUTH.getOpposite()).toArray(Direction[]::new);
//
//    public BranchTreeDecorator(Codec<BranchTreeDecorator> probability) {
//        super(probability);
//    }
//
//
//    public static final TreeDecoratorType<BranchTreeDecorator> BRANCH = AddBranchTreeDecoratorType.register("branch",BranchTreeDecorator.CODEC);
//
//    @Override
//    protected TreeDecoratorType<?> getType() {
//        return BRANCH;
//    }
//
//
//    @Override
//    public void generate(TreeDecorator.Generator generator) {
//        Random random = generator.getRandom();
//        float probability = 0.4f;
//        if (random.nextFloat() >= probability) {
//            return;
//        }
//        ObjectArrayList<BlockPos> list = generator.getLeavesPositions();
//        ObjectArrayList<BlockPos> list2 = generator.getLogPositions();
//        int i = !list.isEmpty() ? Math.max(((BlockPos)list.get(0)).getY() - 1, ((BlockPos)list2.get(0)).getY() + 1) : Math.min(((BlockPos)list2.get(0)).getY() + 1 + random.nextInt(3), ((BlockPos)list2.get(list2.size() - 1)).getY());
//        List list3 = list2.stream().filter(pos -> pos.getY() == i).flatMap(pos -> Stream.of(GENERATE_DIRECTIONS).map(pos::offset)).collect(Collectors.toList());
//        if (list3.isEmpty()) {
//            return;
//        }
//        Collections.shuffle(list3);
//        Optional<BlockPos> optional = list3.stream().filter(pos -> generator.isAir((BlockPos)pos) && generator.isAir(((BlockPos) pos).offset(Direction.SOUTH))).findFirst();
//        if (optional.isEmpty()) {
//            return;
//        }
//        generator.replace(optional.get().add(0, -2, 0), Blocks.BIRCH_LOG.getDefaultState());
//    }
//}
