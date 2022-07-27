package com.mango.anosk.mosk.items.client;

import com.mango.anosk.mosk.items.PulleyItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PulleyBlockItemModel extends AnimatedGeoModel<PulleyItem>{
    @Override
    public Identifier getModelResource(PulleyItem object) {
        return new Identifier("mosk", "geo/pulley.geo.json");
    }

    @Override
    public Identifier getTextureResource(PulleyItem object) {
        return new Identifier("mosk", "textures/block/pulley_block.png");
    }

    @Override
    public Identifier getAnimationResource(PulleyItem animatable) {
        return new Identifier("mosk", "animations/block/pulley.animation.json");
    }
}
