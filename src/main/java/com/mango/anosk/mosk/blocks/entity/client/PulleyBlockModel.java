package com.mango.anosk.mosk.blocks.entity.client;

import com.mango.anosk.mosk.Mosk;
import com.mango.anosk.mosk.blocks.entity.PulleyBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PulleyBlockModel extends AnimatedGeoModel<PulleyBlockEntity>{
    @Override
    public Identifier getModelResource(PulleyBlockEntity object) {
        return new Identifier(Mosk.MOD_ID, "geo/pulley.geo.json");
    }

    @Override
    public Identifier getTextureResource(PulleyBlockEntity object) {
        return new Identifier(Mosk.MOD_ID, "textures/block/pulley_block.png");
    }

    @Override
    public Identifier getAnimationResource(PulleyBlockEntity animatable) {
        return new Identifier(Mosk.MOD_ID, "animations/block/pulley.animation.json");
    }
}
