package com.mango.anosk.mosk;

import com.mango.anosk.mosk.blocks.entity.BlockEntities;
import com.mango.anosk.mosk.blocks.entity.client.PulleyBlockRenderer;
import com.mango.anosk.mosk.items.Items;
import com.mango.anosk.mosk.items.client.PulleyBlockItemRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class MoskClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {


        GeoItemRenderer.registerItemRenderer(Items.PULLEY_ITEM, new PulleyBlockItemRenderer());
        BlockEntityRendererRegistry.register(BlockEntities.PULLEY_BLOCK_ENTITY, PulleyBlockRenderer::new);
    }
}
