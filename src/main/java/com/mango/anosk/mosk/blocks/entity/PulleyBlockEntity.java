package com.mango.anosk.mosk.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class PulleyBlockEntity extends BlockEntity implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);

    public PulleyBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntities.PULLEY_BLOCK_ENTITY, pos, state);
    }

    /**
     * "This function registers the animation controller with the animation data."
     *
     * The first parameter is the animation data. The second parameter is the name of the animation controller. The third
     * parameter is the default animation. The fourth parameter is a predicate that determines whether the animation should
     * be played
     *
     * @param animationData The AnimationData object that is passed to the registerControllers method.
     */
    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<PulleyBlockEntity>(this,"controller", 0, this::predicate));
    }

    /**
     * "If the animation is not playing, play it."
     *
     * The first line of the function is a generic type declaration. This is a Java thing, and it's not important for the
     * purposes of this tutorial
     *
     * @param event The event that triggered the predicate.
     * @return The PlayState.CONTINUE is being returned.
     */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event){
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.pulley.up", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}