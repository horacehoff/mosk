package com.mango.anosk.mosk.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class PulleyItem extends BlockItem implements IAnimatable {
    public AnimationFactory factory = new AnimationFactory(this);

    public PulleyItem(Block block, Settings settings){
        super(block, settings);
    }

    private <E extends IAnimatable>PlayState predicate(AnimationEvent<E> event){
        event.getController().setAnimation(new AnimationBuilder().addAnimation("up", true));

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData){
        animationData.addAnimationController(new AnimationController(this, "controller", 0 , this::predicate));
    }

    @Override
    public AnimationFactory getFactory(){
        return  this.factory;
    }
}
