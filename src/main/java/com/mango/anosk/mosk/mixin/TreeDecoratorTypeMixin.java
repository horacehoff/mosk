package com.mango.anosk.mosk.mixin;


import com.mango.anosk.mosk.world.feature.tree.BranchTreeDecorator;
import com.mango.anosk.mosk.world.feature.tree.MushroomTreeDecorator;
import com.mojang.serialization.Codec;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TreeDecoratorType.class)
public interface TreeDecoratorTypeMixin<P extends TreeDecorator> {

    /**
     * This function is used to register a new branch tree decorator type.
     *
     * @param id The ID of the tree decorator type.
     * @param codec The codec to use for the tree decorator.
     * @return A TreeDecoratorType
     */
    @Invoker("register")
    public static <P extends TreeDecorator> TreeDecoratorType<P> invokeBranchRegister(String id, Codec<BranchTreeDecorator> codec) {
        throw new AssertionError();
    }

    /**
     * "This function is a static method that takes a String and a Codec, and returns a TreeDecoratorType. It is annotated
     * with @Invoker, and the name of the method it invokes is 'register'."
     *
     * The @Invoker annotation is used to tell the compiler that this method is an invoker. The name of the method it
     * invokes is specified in the annotation
     *
     * @param id The id of the tree decorator.
     * @param codec The codec for the tree decorator.
     * @return A TreeDecoratorType
     */
    @Invoker("register")
    public static <P extends TreeDecorator> TreeDecoratorType<P> invokeMushroomRegister(String id, Codec<MushroomTreeDecorator> codec) {
        throw new AssertionError();
    }

}
