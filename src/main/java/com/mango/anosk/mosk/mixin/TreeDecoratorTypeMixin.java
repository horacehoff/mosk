package com.mango.anosk.mosk.mixin;


import com.mango.anosk.mosk.world.feature.tree.BranchTreeDecorator;
import com.mango.anosk.mosk.world.feature.tree.MushroomTreeDecorator;
import com.mojang.serialization.Codec;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import org.checkerframework.checker.nullness.qual.AssertNonNullIfNonNull;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TreeDecoratorType.class)
public interface TreeDecoratorTypeMixin<P extends TreeDecorator> {

    @Invoker("register")
    public static <P extends TreeDecorator> TreeDecoratorType<P> invokeRegister(String id, Codec<BranchTreeDecorator> codec) {
        throw new AssertionError();
    }

    @Invoker("register")
    public static <P extends TreeDecorator> TreeDecoratorType<P> invokeTwoRegister(String id, Codec<MushroomTreeDecorator> codec) {
        throw new AssertionError();
    }

}
