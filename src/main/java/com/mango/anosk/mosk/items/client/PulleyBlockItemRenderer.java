package com.mango.anosk.mosk.items.client;


import com.mango.anosk.mosk.items.PulleyItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class PulleyBlockItemRenderer extends GeoItemRenderer<PulleyItem> {
    public PulleyBlockItemRenderer(){
        super(new PulleyBlockItemModel());
    }
}
