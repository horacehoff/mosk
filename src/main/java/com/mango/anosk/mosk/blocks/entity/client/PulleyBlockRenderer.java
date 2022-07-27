package com.mango.anosk.mosk.blocks.entity.client;

import com.mango.anosk.mosk.blocks.entity.PulleyBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class PulleyBlockRenderer extends GeoBlockRenderer<PulleyBlockEntity> {
    public PulleyBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new PulleyBlockModel());
    }

    @Override
    public RenderLayer getRenderType(PulleyBlockEntity animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
